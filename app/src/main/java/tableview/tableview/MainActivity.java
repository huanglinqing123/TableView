package tableview.tableview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import hlq.tablbeview.OnTableClick;
import hlq.tablbeview.TableView;

public class MainActivity extends AppCompatActivity {

    private TableLayout tableheader;
    private TableLayout tablecontent;
    private TableView tableView;
    private String[] mlistHead={"日期","类型","金额","操作"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        tableView.setTable(6, 4, new OnTableClick() {
            @Override
            public void onTableClickListener(int row, int col) {

            }
        });
        tableView.setTableHead(mlistHead);
        tableView.setTableContent();

    }

    private void init(){
        tableView = findViewById(R.id.tabview);
    }
}
