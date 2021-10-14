package com.example.whatsapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.R;
import com.example.whatsapp.models.MessageModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{
    ArrayList<MessageModel> messageModels;
    Context context;
    String recId;
    int senderViewType=1;
    int receiverViewType=2;
    public ChatAdapter(ArrayList<MessageModel> messageModels, Context context) {
        this.messageModels = messageModels;
        this.context = context;
    }

    public ChatAdapter(ArrayList<MessageModel> messageModels, Context context, String recId) {
        this.messageModels = messageModels;
        this.context = context;
        this.recId = recId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType==senderViewType)
        {View view= LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
            return new senderViewHolder(view);}
        else {
          View view=LayoutInflater.from(context).inflate(R.layout.sample_reviceiver,parent,false);
          return new RecieverViewHolder(view);

        }

    }

    @Override
    public int getItemViewType(int position) {
        if (messageModels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())){

            return senderViewType;
        }
        else {

            return receiverViewType;
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    MessageModel messageModel=messageModels.get(position);
    if (holder.getClass()==senderViewHolder.class){

        ((senderViewHolder)holder).sendMsg.setText(messageModel.getMessage());


    }
    else {
        ((RecieverViewHolder)holder).receiveMsg.setText(messageModel.getMessage());

    }

holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View v) {


        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle("Delete");
        builder1.setMessage("Are you want to sure to delete this message.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FirebaseDatabase database=FirebaseDatabase.getInstance();
                        String senderRoom=FirebaseAuth.getInstance().getUid()+recId;
                        database.getReference().child("chats").child(senderRoom).child(messageModel.getMessageId()).setValue(null);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
        return false;
    }


});


    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }

    public class RecieverViewHolder extends RecyclerView.ViewHolder{
        TextView receiveMsg,recieverTime;
        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);
            receiveMsg=itemView.findViewById(R.id.revicertxt);
            recieverTime=itemView.findViewById(R.id.recivertime);
        }
    }

    public class senderViewHolder extends RecyclerView.ViewHolder{
        TextView sendMsg,sendTime;


        public senderViewHolder(@NonNull View itemView) {
            super(itemView);
            sendMsg=itemView.findViewById(R.id.sendtxt);
            sendTime=itemView.findViewById(R.id.sendtime);
        }
    }
}
