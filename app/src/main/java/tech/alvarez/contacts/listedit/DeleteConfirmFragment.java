package tech.alvarez.contacts.listedit;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import tech.alvarez.contacts.R;
import tech.alvarez.contacts.utils.Constants;

public class DeleteConfirmFragment extends DialogFragment {

    private ListContract.DeleteListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final long personId = getArguments().getLong(Constants.PERSON_ID);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.are_you_sure);

        builder.setPositiveButton(android.R.string.ok, (dialogInterface, i) -> mListener.setConfirm(true, personId));
        builder.setNegativeButton(android.R.string.cancel, (dialogInterface, i) -> mListener.setConfirm(false, personId));
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (ListContract.DeleteListener) context;
    }
}
