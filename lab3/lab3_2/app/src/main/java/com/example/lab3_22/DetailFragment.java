package com.example.lab3_22;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {
    String[] countries_description = { "Украина – это большая страна в Восточной Европе, известная православными церквями, черноморскими курортами и лесистыми горами. В Киеве, столице страны, расположен Софийский собор, украшенный золотыми куполами, мозаикой XI века и фресками. Над рекой Днепр возвышается монастырь Киево-Печерская лавра, место паломничества многих православных христиан. В ее скитах хранятся " +
            "религиозные реликвии, а в катакомбах – мумифицированные останки православных монахов.",
            "Канада – североамериканская страна, простирающаяся от границы с США на юге до полярного круга на севере. Крупнейшие города – многолюдный Торонто, расположенный на западном побережье центр киноиндустрии Ванкувер, франкоязычные Монреаль и Квебек и столица страны Оттава. Значительную часть Канады занимают территории с дикой природой, в том числе национальный парк Банф в Скалистых горах со множеством озер. " +
                    "Кроме того, здесь находится всемирно известный Ниагарский водопад.",
            "Казахстан – государство в Центральной Азии, которое раньше входило в состав СССР. Оно простирается от Каспийского моря на западе до Алтайских гор на востоке, где проходит граница с Китаем и Россией. Алма-Ата – самый большой город и важный торговый центр страны. Среди достопримечательностей города стоит отметить Вознесенский собор Русской православной церкви, построенный при Николае II, и Центральный государственный музей Республики Казахстан, тысячи" +
                    " экспонатов которого позволяют получить представление об истории государства."};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    // обновление текстового поля
    public void setSelectedItem(String selectedItem) {
        TextView view = (TextView) getView().findViewById(R.id.detailsText);
        System.out.println(selectedItem);
        if (selectedItem.equals("Украина")){
            TextView view2 = (TextView) getView().findViewById(R.id.textView);
            view2.setText(countries_description[0]);
            ImageView imageView=(ImageView)getView().findViewById(R.id.imageView);
            imageView.setImageResource(R.drawable.ukr);
        }
        else if (selectedItem.equals("Канада")){
            TextView view2 = (TextView) getView().findViewById(R.id.textView);
            view2.setText(countries_description[1]);
            ImageView imageView=(ImageView)getView().findViewById(R.id.imageView);
            imageView.setImageResource(R.drawable.can);
        }
        else if (selectedItem.equals("Казахстан")){
            TextView view2 = (TextView) getView().findViewById(R.id.textView);
            view2.setText(countries_description[2]);
            ImageView imageView=(ImageView)getView().findViewById(R.id.imageView);
            imageView.setImageResource(R.drawable.kaz);
        }
        view.setText(selectedItem);
    }
}
