package pilet.com.primeraapp.app;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;

import br.liveo.interfaces.OnItemClickListener;
import br.liveo.interfaces.OnPrepareOptionsMenuLiveo;
import br.liveo.model.HelpLiveo;
import br.liveo.navigationliveo.NavigationLiveo;

public class MainActivity extends NavigationLiveo implements OnItemClickListener {

    private HelpLiveo mHelpLiveo;
    @Override
    public void onInt(Bundle savedInstanceState) {
        // User Information
        this.userName.setText("Ariel Torres");
        this.userEmail.setText("ariel.perez@udb.edu.sv");
        this.userPhoto.setImageResource(R.drawable.ic_rudsonlive);
        this.userBackground.setImageResource(R.drawable.ic_user_background_first);

        // Creating items navigation
        mHelpLiveo = new HelpLiveo();
        mHelpLiveo.add("Inicio", R.mipmap.ic_home, 7);
        mHelpLiveo.addSubHeader("Categorías"); //Item subHeader
        mHelpLiveo.add("Cateogoría 1", R.mipmap.ic_launcher);

        //with(this, Navigation.THEME_DARK). add theme dark
        //with(this, Navigation.THEME_LIGHT). add theme light

        with(this) // default theme is dark
                .startingPosition(0) //Starting position in the list
                .addAllHelpItem(mHelpLiveo.getHelp())
                .footerItem("Configuración", R.mipmap.ic_settings)
                .setOnClickUser(onClickPhoto)
                .setOnPrepareOptionsMenu(onPrepare)
                .setOnClickFooter(onClickFooter)
                .build();
    }
    @Override
    public void onItemClick(int position) {
        FragmentTransaction trans = MainActivity.this.getFragmentManager().beginTransaction();
        System.out.println("Posicion: " + position);
        if ((mHelpLiveo.get(position).isHeader())) {
            System.out.println("Header ");
        } else {
            System.out.println("No Header");
        }
        switch (position) {
            case 0:
                    Inicio inicio = new Inicio();
                    trans.replace(R.id.container, inicio, "Inicio");

                break;
            case 2:
                Categoria1 categoria1 = new Categoria1();
                trans.replace(R.id.container, categoria1, "categoria 1");
                break;
            case 3:
                BlankFragment blankFragment = new BlankFragment();
                trans.replace(R.id.container, blankFragment, "categoria 1");
                break;
        }
        trans.commit();

    }
    private OnPrepareOptionsMenuLiveo onPrepare = new OnPrepareOptionsMenuLiveo() {
        @Override
        public void onPrepareOptionsMenu(Menu menu, int position, boolean visible) {
        }
    };
    private View.OnClickListener onClickPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeDrawer();
        }
    };
    private View.OnClickListener onClickFooter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeDrawer();
        }
    };

}
