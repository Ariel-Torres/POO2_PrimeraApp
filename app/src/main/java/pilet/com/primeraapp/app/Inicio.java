package pilet.com.primeraapp.app;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pilet.com.primeraapp.app.Adapters.AdapterArtistas;
import pilet.com.primeraapp.app.Model.Artist;
import pilet.com.primeraapp.app.Model.Server;
import pilet.com.primeraapp.app.Singleton.AppSingleton;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class Inicio extends Fragment {
    ProgressDialog pDialog= null;
    Context context;
    public Inicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Code here
        context = getActivity().getApplicationContext();
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();
        JsonObjectRequest request =  new JsonObjectRequest(
                Server.serverUrl + Server.Artists,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<Artist> artistas = parseJson(response);
                        AdapterArtistas adapterArtistas =  new AdapterArtistas(getActivity().getApplicationContext(),R.layout.row_artista,artistas);
                        ListView lista = getActivity().findViewById(R.id.Artistaslst);
                        lista.setAdapter(adapterArtistas);
                        pDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error_network_timeout),
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error_auth_fail),
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error_server),
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error_network),
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(context,
                                    context.getString(R.string.error_no_id) + " "+ error,
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
        AppSingleton.getInstance(context).addToRequestQueue(request);
    }
    public ArrayList<Artist> parseJson(JSONObject jsonObject) {
        // Variables locales
        ArrayList<Artist> artistas = new ArrayList<>();
        JSONArray jsonArray = null;

        try {
            // Obtener el array del objeto
            jsonArray = jsonObject.getJSONArray("artist");

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject objeto = jsonArray.getJSONObject(i);

                    Artist clt = new Artist(
                            objeto.getString("artistId"),
                            objeto.getString("name"));

                    artistas.add(clt);


                } catch (JSONException e) {
                    Log.e(TAG, "Error de parsing json: " + e.getMessage());
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return artistas;
    }
}
