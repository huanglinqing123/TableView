package hlq.tablbeview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


/**
 * Created by  Huanglinqing on 2018/8/20/020.
 * Email:1306214077@qq.com
 */

public class TableView extends RelativeLayout {

    private int width;//屏幕宽
    private int height;//屏幕高
    private int rows;//设置行数
    private int columns;//设置列数
    private View view;
    private TableLayout tableheader;
    private TableLayout tabcontent;
    private Context mcontenx;
    OnTableClick onTableClick;
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private final int FP = ViewGroup.LayoutParams.MATCH_PARENT;

    public TableView(Context context) {
        super(context);
        this.mcontenx = context;
        init(context);
    }

    public TableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mcontenx = context;
        init(context);
    }

    public TableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mcontenx = context;
        init(context);
    }

    /**
     * 初始化宽高
     */
    private void init(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
    }

    /**
     * 设置表格行数和列数
     *
     * @param rows
     * @param columns
     * @param onTableClick
     */
    public void setTable(int rows, int columns, OnTableClick onTableClick) {
        this.rows = rows;
        this.columns = columns;
        this.onTableClick = onTableClick;
    }


    /**
     * 设置表头
     *
     * @param mlistHead
     */
    public void setTableHead(String[] mlistHead) {
        initHead(mlistHead);
    }

    /**
     * 设置表格
     */
    public void setTableContent() {
        inittablecontent();
    }

    /**
     * 初始化表格内容
     */
    private void inittablecontent() {
        LayoutInflater.from(mcontenx).inflate(R.layout.tableview, this);
        tabcontent = (TableLayout) this.findViewById(R.id.table_content);
        tabcontent.setStretchAllColumns(true);
        for (int row = 0; row < rows; row++) {

            TableRow mTableRow = new TableRow(mcontenx);
            mTableRow.setBackgroundColor(Color.rgb(255, 255, 255));

            int tempWith = 0;
            for (int col = 0; col < columns; col++) {
                TextView mColumn = new TextView(mcontenx);
                mColumn.setText("日期时间");
                mColumn.setHeight(100);
                if (col == 0) {
                    tempWith = 480;
                    mColumn.setWidth(tempWith);
                    tempWith = (width - tempWith) / (columns - 1);
                } else {
                    mColumn.setWidth(tempWith);
                }

                if (col < columns - 1) {
                    mColumn.setBackgroundResource(R.drawable.shapee_left);
                } else {
                    mColumn.setBackgroundResource(R.drawable.shapee_right);
                }
                mColumn.setGravity(Gravity.CENTER);
                mTableRow.addView(mColumn, col);

                mColumn.setOnClickListener(new myListener(row, col, onTableClick));

            }

            tabcontent.addView(mTableRow, new TableLayout.LayoutParams(WC, FP));
        }
    }

    /**
     * 初始化表头
     *
     * @param mlistHead
     */
    private void initHead(String[] mlistHead) {
        LayoutInflater.from(mcontenx).inflate(R.layout.tableview, this);
        tableheader = (TableLayout) this.findViewById(R.id.table_head);
        tableheader.setStretchAllColumns(true);
        TableRow mTableRow = new TableRow(mcontenx);
        mTableRow.setBackgroundColor(Color.rgb(255, 255, 255));
        int tempWith = 0;
        for (int col = 0; col < columns; col++) {

            TextView mColumn = new TextView(mcontenx);
            mColumn.setHeight(100);
            if (col == 0) {
                tempWith = 480;
                mColumn.setWidth(tempWith);
                tempWith = (width - tempWith) / (columns - 1);
            } else {
                mColumn.setWidth(tempWith);
            }


            mColumn.setBackgroundResource(R.drawable.shapee_head);
            mColumn.setGravity(Gravity.CENTER);
            mColumn.setTextColor(Color.rgb(255, 255, 255));
            if (mlistHead.length > col)
                mColumn.setText(mlistHead[col]);
            mTableRow.addView(mColumn, col);
        }
        tableheader.addView(mTableRow, new TableLayout.LayoutParams(WC, FP));
    }

    /**
     * 自定义监听事件
     */
    class myListener implements OnClickListener {
        int col = 0;
        int row = 0;
        OnTableClick mOnTableClick;

        public myListener(int row, int col, OnTableClick mOnTableClick) {
            super();
            this.row = row;
            this.col = col;
            this.mOnTableClick = mOnTableClick;
        }

        @Override
        public void onClick(View v) {
            mOnTableClick.onTableClickListener(row, col);
        }
    }
}
