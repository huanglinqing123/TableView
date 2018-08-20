package hlq.tablbeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
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

    /**
     * xml中可配置的属性
     */
    private boolean head_is_show = false;
    private int headbackground = R.drawable.shapee_head;
    private int head_text_color = Color.rgb(255, 255, 255);
    private String head_text;
    private int rownum;
    private int colnum;
    private int table_head_background = R.drawable.shapee_head;
    private int table_head_textcolor = Color.rgb(255, 255, 255);
    private int table_content_backgroumd = Color.WHITE;
    private int table_content_textcolor = Color.rgb(255, 255, 255);
    private int head_height ;
    private int tablehead_height;
    private int tablecontent_height;

    private int width;//屏幕宽
    private int height;//屏幕高
    private int rows;//设置行数
    private int columns;//设置列数
    private View view;
    private TableLayout tableheader;
    private TableLayout tabcontent;
    private TextView viewheader;
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

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.tableview);
        rownum = typedArray.getInt(R.styleable.tableview_rownum, 0);
        rows = rownum;
        colnum = typedArray.getInt(R.styleable.tableview_colnum, 0);
        columns = colnum;
        head_is_show = typedArray.getBoolean(R.styleable.tableview_head_is_show, false);
        head_text = typedArray.getString(R.styleable.tableview_headtext);
        head_height = typedArray.getInteger(R.styleable.tableview_head_height,80);
        tablehead_height = typedArray.getInteger(R.styleable.tableview_tablehead_height,100);
        tablecontent_height = typedArray.getInteger(R.styleable.tableview_tablecontent_height,100);
        typedArray.recycle();
        init(context);
    }

    public TableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mcontenx = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.tableview);
        rownum = typedArray.getInt(R.styleable.tableview_rownum, 0);
        rows = rownum;
        colnum = typedArray.getInt(R.styleable.tableview_colnum, 0);
        columns = colnum;
        head_is_show = typedArray.getBoolean(R.styleable.tableview_head_is_show, false);
        head_text = typedArray.getString(R.styleable.tableview_headtext);
        head_height = typedArray.getInteger(R.styleable.tableview_head_height,80);
        tablehead_height = typedArray.getInteger(R.styleable.tableview_tablehead_height,100);
        tablecontent_height = typedArray.getInteger(R.styleable.tableview_tablecontent_height,100);
        typedArray.recycle();
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
     *
     * @param onTableClick
     */
    public void setTable(OnTableClick onTableClick) {
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
    public void setTableContent(String[] mlistContent) {
        inittablecontent(mlistContent);
    }

    /**
     * 初始化表格内容
     */
    private void inittablecontent(String[] mlistContent) {
        LayoutInflater.from(mcontenx).inflate(R.layout.tableview, this);
        tabcontent = (TableLayout) this.findViewById(R.id.table_content);
        tabcontent.setStretchAllColumns(true);
        for (int row = 0; row < rows; row++) {
            TableRow mTableRow = new TableRow(mcontenx);

            int tempWith = 0;
            for (int col = 0; col < columns; col++) {
                TextView mColumn = new TextView(mcontenx);
                mColumn.setSingleLine(true);
                mColumn.setSelected(true);
                mColumn.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                mColumn.setText(mlistContent[col]);
                mColumn.setTextColor(Color.BLACK);
                mColumn.setHeight(tablecontent_height);
                if (col == 0) {
                    tempWith = (width - tempWith) / (columns);
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
        viewheader = this.findViewById(R.id.viewheader);
        if (head_is_show) {
            viewheader.setVisibility(View.VISIBLE);
            viewheader.setText(TextUtils.isEmpty(head_text) ? "默认标题" : head_text);
            viewheader.setTextColor(Color.WHITE);
            viewheader.setHeight(head_height);
        }else{
            viewheader.setVisibility(View.GONE);
        }
        TableRow mTableRow = new TableRow(mcontenx);
        int tempWith = 0;
        for (int col = 0; col < columns; col++) {
            TextView mColumn = new TextView(mcontenx);
            mColumn.setSingleLine(true);
            mColumn.setSelected(true);
            mColumn.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            mColumn.setTextColor(Color.WHITE);
            mColumn.setHeight(tablehead_height);
            if (col == 0) {
                tempWith = (width - tempWith) / (columns);
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
