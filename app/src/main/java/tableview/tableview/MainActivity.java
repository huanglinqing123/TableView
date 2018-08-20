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
    private String[] mlistHead={"id","姓名","学号","性别"};
    private String[] mlistContent={"1","黄林晴","2014211617","男"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        tableView.setTable(new OnTableClick() {
            @Override
            public void onTableClickListener(int row, int col) {

            }
        });
        tableView.setTableHead(mlistHead);
        tableView.setTableContent(mlistContent);

    }

    private void init(){
        tableView = findViewById(R.id.tabview);
    }
}
