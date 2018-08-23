package pilet.com.primeraapp.app.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pilet.com.primeraapp.app.Model.Artist;
import pilet.com.primeraapp.app.R;

public class AdapterArtistas extends ArrayAdapter<Artist>{
    private static final String TAG = "Artist-Adapter";
    ArrayList<Artist> items;
    public AdapterArtistas(final Context context, int resource, ArrayList<Artist> objects) {
        super(context, resource,objects);
        items = objects;
    }
    static class ViewHolder {
        TextView ID;
        TextView Name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Artist artist = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_artista, parent, false);
            viewHolder.ID = (TextView) convertView.findViewById(R.id.ArtistID);
            viewHolder.Name = (TextView) convertView.findViewById(R.id.ArtistName);

            viewHolder.ID.setText(artist.getArtistId());
            viewHolder.Name.setText(artist.getName());
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.ID.setText(artist.getArtistId());
            viewHolder.Name.setText(artist.getName());

        }

        return convertView;

    }

}
