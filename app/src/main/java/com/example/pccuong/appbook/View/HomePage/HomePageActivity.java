package com.example.pccuong.appbook.View.HomePage;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.design.widget.TabLayout;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.Menu;
        import android.view.MenuItem;

        import com.example.pccuong.appbook.Adapter.ViewBagerHomePage;
        import com.example.pccuong.appbook.R;
        import com.example.pccuong.appbook.View.Login.LoginActivity;

/**
 * Created by PCCuong on 11/30/2016.
 */

public class HomePageActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.tabviewhomepage);
        tabLayout = (TabLayout) findViewById(R.id.tablayoutList);
        ViewBagerHomePage viewBagerHomePage = new ViewBagerHomePage(getSupportFragmentManager());
        viewPager.setAdapter(viewBagerHomePage);
        viewBagerHomePage.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id =  item.getItemId();
        switch (id){
            case R.id.itdangnhap:
                Intent intentLogin = new Intent(this, LoginActivity.class);
                startActivity(intentLogin);


 default: return  true;
        }

    }
}
