package rbk.sf.springpetclinic.util;

import java.util.Comparator;
import java.util.List;

public class PropertyComparator {
    public static <T>  void sort(List<T> list){
        list.sort(Comparator.comparing(obj ->{
           try{
               return (String) obj.getClass().getMethod("getName").invoke(obj);
           }catch(Exception e){
               return "";
           }
        }));
    }
}
