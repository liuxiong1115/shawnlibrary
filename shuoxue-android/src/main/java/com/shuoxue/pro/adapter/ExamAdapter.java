package com.shuoxue.pro.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuoxue.pro.R;
import com.shuoxue.pro.model.Exam;

/**
 * 作者：${刘雄} on 2017/1/21 23:08
 * 邮箱：orange_lx0120@126.com
 */
public class ExamAdapter extends BaseQuickAdapter<Exam, BaseViewHolder> {

    public ExamAdapter() {
        super( R.layout.adapter_item_exam_layout , null);
    }


    @Override
    protected void convert(BaseViewHolder helper, Exam item) {
        helper.setText(R.id.examName , item.getExamName());
        helper.setText(R.id.examTotalTest , "共" + item.getTotTests() + "题");
        helper.setText(R.id.endTime , item.getEndTime() + "");
        helper.setText(R.id.syTime , item.getEndTime() + "");
        helper.setText(R.id.soce , item.getExamMinutes() + "");
    }
}
