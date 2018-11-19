package meuveterinario.filipe.com.br.meuveterinrio;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

public class ViewPagerTeste extends ViewPager {


    public ViewPagerTeste(@NonNull Context context) {
        super(context);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll){
        super.setCurrentItem(item, false);
    }

    @Override
    public void setCurrentItem(int item){
        super.setCurrentItem(item, false);
    }

}
