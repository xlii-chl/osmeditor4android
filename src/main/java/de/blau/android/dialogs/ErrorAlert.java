package de.blau.android.dialogs;

import static de.blau.android.contract.Constants.LOG_TAG_LEN;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import de.blau.android.AsyncResult;
import de.blau.android.ErrorCodes;
import de.blau.android.R;
import de.blau.android.listener.DoNothingListener;
import de.blau.android.util.ImmersiveDialogFragment;
import de.blau.android.util.ThemeUtils;
import de.blau.android.util.Util;

/**
 * Simple alert dialog with an OK button that does nothing
 * 
 * @author simon
 *
 */
public class ErrorAlert extends ImmersiveDialogFragment {

    private static final int    TAG_LEN   = Math.min(LOG_TAG_LEN, ErrorAlert.class.getSimpleName().length());
    private static final String DEBUG_TAG = ErrorAlert.class.getSimpleName().substring(0, TAG_LEN);

    private static final String TITLE            = "title";
    private static final String MESSAGE          = "message";
    private static final String ORIGINAL_MESSAGE = "original_message";

    private int    titleId;
    private int    messageId;
    private String originalMessage;

    /**
     * Display a simple alert dialog with an OK button that does nothing
     * 
     * @param activity the calling Activity
     * @param errorCode the error code
     */
    public static void showDialog(@NonNull FragmentActivity activity, int errorCode) {
        showDialog(activity, errorCode, null);
    }

    /**
     * Display a simple alert dialog with an OK button that does nothing
     * 
     * @param activity the calling Activity
     * @param result a ReadAsyncResult instance
     */
    public static void showDialog(@NonNull FragmentActivity activity, AsyncResult result) {
        showDialog(activity, result.getCode(), result.getMessage());
    }

    /**
     * Display a simple alert dialog with an OK button that does nothing
     * 
     * @param activity the calling Activity
     * @param errorCode the error code
     * @param msg a message
     */
    public static void showDialog(@NonNull FragmentActivity activity, int errorCode, @Nullable String msg) {
        dismissDialog(activity, errorCode);

        FragmentManager fm = activity.getSupportFragmentManager();
        ErrorAlert alertDialogFragment = newInstance(errorCode, msg);
        try {
            String tag = getTag(errorCode);
            if (alertDialogFragment != null && tag != null) {
                alertDialogFragment.show(fm, tag);
            } else {
                Log.e(DEBUG_TAG, "Unable to create dialog for value " + errorCode);
            }
        } catch (IllegalStateException isex) {
            Log.e(DEBUG_TAG, "showDialog", isex);
        }
    }

    /**
     * Dismiss the dialog
     * 
     * @param activity the calling Activity
     * @param errorCode the errorCode the dialog was for
     */
    private static void dismissDialog(@NonNull FragmentActivity activity, int errorCode) {
        String tag = getTag(errorCode);
        if (tag != null) {
            de.blau.android.dialogs.Util.dismissDialog(activity, tag);
        }
    }

    /**
     * Map error codes to tags
     * 
     * @param errorCode the error code
     * @return a tag
     */
    @Nullable
    private static String getTag(int errorCode) {
        switch (errorCode) {
        case ErrorCodes.NO_LOGIN_DATA:
            return "alert_no_login_data";
        case ErrorCodes.NO_CONNECTION:
            return "alert_no_connection";
        case ErrorCodes.SSL_HANDSHAKE:
            return "ssl_handshake_failed";
        case ErrorCodes.UPLOAD_PROBLEM:
            return "alert_upload_problem";
        case ErrorCodes.BAD_REQUEST:
            return "alert_bad_request";
        case ErrorCodes.DATA_CONFLICT:
            return "alert_data_conflict";
        case ErrorCodes.API_OFFLINE:
            return "alert_api_offline";
        case ErrorCodes.OUT_OF_MEMORY:
            return "alert_out_of_memory";
        case ErrorCodes.OUT_OF_MEMORY_DIRTY:
            return "alert_out_of_memory_dirty";
        case ErrorCodes.INVALID_DATA_RECEIVED:
            return "alert_invalid_data_received";
        case ErrorCodes.INVALID_DATA_READ:
            return "alert_invalid_data_read";
        case ErrorCodes.FILE_WRITE_FAILED:
            return "alert_file_write_failed";
        case ErrorCodes.NAN:
            return "alert_nan";
        case ErrorCodes.INVALID_BOUNDING_BOX:
            return "invalid_bounding_box";
        case ErrorCodes.BOUNDING_BOX_TOO_LARGE:
            return "bounding_box_too_large";
        case ErrorCodes.INVALID_LOGIN:
            return "invalid_login";
        case ErrorCodes.NOT_FOUND:
            return "not_found";
        case ErrorCodes.UNKNOWN_ERROR:
            return "unknown";
        case ErrorCodes.NO_DATA:
            return "no_data";
        case ErrorCodes.REQUIRED_FEATURE_MISSING:
            return "required_feature_missing";
        case ErrorCodes.APPLYING_OSC_FAILED:
            return "applying_osc_failed";
        case ErrorCodes.CORRUPTED_DATA:
            return "alert_corrupt_data";
        case ErrorCodes.DOWNLOAD_LIMIT_EXCEEDED:
            return "download_limit_exceeded";
        case ErrorCodes.UPLOAD_LIMIT_EXCEEDED:
            return "upload_limit_exceeded";
        case ErrorCodes.DUPLICATE_TAG_KEY:
            return "alert_duplicate_tag_key";
        case ErrorCodes.UPLOAD_BOUNDING_BOX_TOO_LARGE:
            return "alert_bounding_box_too_large";
        case ErrorCodes.TOO_MANY_WAY_NODES:
            return "alert_too_many_way_nodes";
        case ErrorCodes.UPLOAD_WAY_NEEDS_ONE_NODE:
            return "alert_way_needs_one_node";
        default:
            // nothing
        }
        return null;
    }

    /**
     * Get a new instance of an ErrorAlert dialog
     * 
     * @param errorCode the error code
     * @param msg an optional message
     * @return a new instance of an ErrorAlert dialog or null
     */
    @Nullable
    private static ErrorAlert newInstance(int errorCode, @Nullable String msg) {
        switch (errorCode) {
        case ErrorCodes.NO_LOGIN_DATA:
            return createNewInstance(R.string.no_login_data_title, R.string.no_login_data_message, msg);
        case ErrorCodes.NO_CONNECTION:
            return createNewInstance(R.string.no_connection_title, R.string.no_connection_message, msg);
        case ErrorCodes.SSL_HANDSHAKE:
            return createNewInstance(R.string.no_connection_title, R.string.ssl_handshake_failed, msg);
        case ErrorCodes.UPLOAD_PROBLEM:
            return createNewInstance(R.string.upload_problem_title, R.string.upload_problem_message, msg);
        case ErrorCodes.BAD_REQUEST:
            return createNewInstance(R.string.upload_problem_title, R.string.bad_request_message, msg);
        case ErrorCodes.DATA_CONFLICT:
            return createNewInstance(R.string.data_conflict_title, R.string.data_conflict_message, msg);
        case ErrorCodes.API_OFFLINE:
            return createNewInstance(R.string.api_offline_title, R.string.api_offline_message, msg);
        case ErrorCodes.OUT_OF_MEMORY:
            return createNewInstance(R.string.out_of_memory_title, R.string.out_of_memory_message, msg);
        case ErrorCodes.OUT_OF_MEMORY_DIRTY:
            return createNewInstance(R.string.out_of_memory_title, R.string.out_of_memory_dirty_message, msg);
        case ErrorCodes.INVALID_DATA_RECEIVED:
            return createNewInstance(R.string.invalid_data_received_title, R.string.invalid_data_received_message, msg);
        case ErrorCodes.INVALID_DATA_READ:
            return createNewInstance(R.string.invalid_data_read_title, R.string.invalid_data_read_message, msg);
        case ErrorCodes.FILE_WRITE_FAILED:
            return createNewInstance(R.string.file_write_failed_title, R.string.file_write_failed_message, msg);
        case ErrorCodes.NAN:
            return createNewInstance(R.string.location_nan_title, R.string.location_nan_message, msg);
        case ErrorCodes.INVALID_BOUNDING_BOX:
            return createNewInstance(R.string.invalid_bounding_box_title, R.string.invalid_bounding_box_message, msg);
        case ErrorCodes.BOUNDING_BOX_TOO_LARGE:
            return createNewInstance(R.string.bounding_box_too_large_title, R.string.bounding_box_too_large_message, msg);
        case ErrorCodes.INVALID_LOGIN:
            return createNewInstance(R.string.wrong_login_data_title, R.string.wrong_login_data_message, msg);
        case ErrorCodes.NOT_FOUND:
            return createNewInstance(R.string.not_found_title, R.string.not_found_message, msg);
        case ErrorCodes.UNKNOWN_ERROR:
            return createNewInstance(R.string.unknown_error_title, R.string.unknown_error_message, msg);
        case ErrorCodes.NO_DATA:
            return createNewInstance(R.string.no_data_title, R.string.no_data_message, msg);
        case ErrorCodes.REQUIRED_FEATURE_MISSING:
            return createNewInstance(R.string.required_feature_missing_title, R.string.required_feature_missing_message, msg);
        case ErrorCodes.APPLYING_OSC_FAILED:
            return createNewInstance(R.string.applying_osc_failed_title, R.string.applying_osc_failed_message, msg);
        case ErrorCodes.CORRUPTED_DATA:
            return createNewInstance(R.string.corrupted_data_title, R.string.corrupted_data_message, msg);
        case ErrorCodes.DOWNLOAD_LIMIT_EXCEEDED:
            return createNewInstance(R.string.download_limit_title, R.string.download_limit_message, msg);
        case ErrorCodes.UPLOAD_LIMIT_EXCEEDED:
            return createNewInstance(R.string.upload_limit_title, R.string.upload_limit_message, msg);
        case ErrorCodes.DUPLICATE_TAG_KEY:
            return createNewInstance(R.string.duplicate_tag_key_title, R.string.duplicate_tag_key_message, msg);
        case ErrorCodes.UPLOAD_BOUNDING_BOX_TOO_LARGE:
            return createNewInstance(R.string.upload_bounding_box_too_large_title, R.string.upload_bounding_box_too_large_message, msg);
        case ErrorCodes.TOO_MANY_WAY_NODES:
            return createNewInstance(R.string.attempt_to_add_too_many_way_nodes_title, R.string.attempt_to_add_too_many_way_nodes_message, msg);
        case ErrorCodes.UPLOAD_WAY_NEEDS_ONE_NODE:
            return createNewInstance(R.string.upload_way_needs_one_node_title, R.string.upload_way_needs_one_node_message, msg);
        default:
            // ignore
        }
        return null;
    }

    /**
     * Create a new instance of an ErrorAlert dialog
     * 
     * @param titleId the title resource id
     * @param messageId the message resource id
     * @param msg an optional message
     * @return a new instance of an ErrorAlert dialog
     */
    @NonNull
    private static ErrorAlert createNewInstance(final int titleId, final int messageId, @Nullable String msg) {
        ErrorAlert f = new ErrorAlert();

        Bundle args = new Bundle();
        args.putSerializable(TITLE, titleId);
        args.putInt(MESSAGE, messageId);
        if (msg != null) {
            args.putString(ORIGINAL_MESSAGE, msg);
        }

        f.setArguments(args);
        f.setShowsDialog(true);

        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleId = Util.getSerializeable(getArguments(), TITLE, Integer.class);
        messageId = getArguments().getInt(MESSAGE);
        originalMessage = getArguments().getString(ORIGINAL_MESSAGE);
    }

    @NonNull
    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(ThemeUtils.getResIdFromAttribute(getActivity(), R.attr.alert_dialog));
        builder.setTitle(titleId);
        if (messageId != 0) {
            String message = getString(messageId);
            if (originalMessage != null) {
                message = message + "<p/><i>" + originalMessage + "</i>";
            }
            builder.setMessage(Util.fromHtml(message));
        }
        DoNothingListener doNothingListener = new DoNothingListener();
        builder.setPositiveButton(R.string.okay, doNothingListener);
        return builder.create();
    }
}
