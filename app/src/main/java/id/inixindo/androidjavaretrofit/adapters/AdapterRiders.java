package id.inixindo.androidjavaretrofit.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.inixindo.androidjavaretrofit.R;
import id.inixindo.androidjavaretrofit.activities.EditRiderActivity;
import id.inixindo.androidjavaretrofit.activities.MainActivity;
import id.inixindo.androidjavaretrofit.apis.ApiRequestData;
import id.inixindo.androidjavaretrofit.apis.RetrofitServer;
import id.inixindo.androidjavaretrofit.models.ModelResponse;
import id.inixindo.androidjavaretrofit.models.ModelRiders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterRiders extends RecyclerView.Adapter<AdapterRiders.HolderRider> {
    private Context context;
    private List<ModelRiders> listRiders;

    public AdapterRiders(Context context, List<ModelRiders> listRiders) {
        this.context = context;
        this.listRiders = listRiders;
    }

    @Override
    public HolderRider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_riders, parent, false);
        HolderRider holderRider = new HolderRider(view);
        return holderRider;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderRider holder, int position) {
        ModelRiders modelRiders = listRiders.get(position);

        holder.textViewId.setText(String.valueOf(modelRiders.getId()));
        holder.textViewNama.setText(modelRiders.getNama());
        holder.textViewSponsor.setText(modelRiders.getSponsor());
        holder.textViewNegara.setText(modelRiders.getNegara());
        holder.textViewNomor.setText(modelRiders.getNomor());

        Glide.with(context).load(modelRiders.getFoto()).placeholder(R.mipmap.user).into(holder.imageViewFoto);
    }

    @Override
    public int getItemCount() {
        if (listRiders != null) {
            return listRiders.size();
        } else {
            Toast.makeText(context, "Data is not available", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    public class HolderRider extends RecyclerView.ViewHolder {
        TextView textViewId, textViewNama, textViewSponsor, textViewNegara, textViewNomor;
        ImageView imageViewFoto;

        public HolderRider(@NonNull View itemView) {
            super(itemView);

            textViewId = itemView.findViewById(R.id.textViewId);
            textViewNama = itemView.findViewById(R.id.textViewNama);
            textViewSponsor = itemView.findViewById(R.id.textViewSponsor);
            textViewNegara = itemView.findViewById(R.id.textViewNegara);
            textViewNomor = itemView.findViewById(R.id.textViewNomor);
            imageViewFoto = itemView.findViewById(R.id.imageViewRider);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder message = new AlertDialog.Builder(context);
                    message.setTitle("Choose One!");
                    message.setMessage("What operation do you want to perform?");
                    message.setCancelable(true);
                    message.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            confirmDeleteRider();
                        }
                    });
                    message.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent sendIntent = new Intent(context, EditRiderActivity.class);
                            sendIntent.putExtra("xID", textViewId.getText().toString());
                            sendIntent.putExtra("xNama", textViewNama.getText().toString());
                            sendIntent.putExtra("xNomor", textViewNomor.getText().toString());
                            sendIntent.putExtra("xSponsor", textViewSponsor.getText().toString());
                            sendIntent.putExtra("xNegara", textViewNegara.getText().toString());
                            context.startActivity(sendIntent);
                        }
                    });

                    message.show();
                    return false;
                }
            });
        }

        private void confirmDeleteRider() {
            AlertDialog.Builder confirmDelete = new AlertDialog.Builder(context);
            confirmDelete.setTitle("Warning!");
            confirmDelete.setMessage("Are you sure to delete this rider?");
            confirmDelete.setCancelable(true);
            confirmDelete.setNegativeButton("Cancel", null);
            confirmDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    deleteRider();
                    dialog.dismiss();
                    ((MainActivity) context).getAllRiders();
                }
            });
            confirmDelete.show();
        }

        private void deleteRider() {
            int id = Integer.parseInt(textViewId.getText().toString());

            ApiRequestData apiRequestData = RetrofitServer.connectRetrofit().create(ApiRequestData.class);
            Call<ModelResponse> responseCall = apiRequestData.deleteData(id);

            responseCall.enqueue(new Callback<ModelResponse>() {
                @Override
                public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                    int code = response.body().getCode();
                    String message = response.body().getMessage();

                    if (code == 1) {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ModelResponse> call, Throwable t) {
                    Toast.makeText(context, "Failed connect to server!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
