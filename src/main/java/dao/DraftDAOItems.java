package dao;

import configuration.ConfigProperties;
import javafx.scene.control.Alert;
import model.Item;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DraftDAOItems implements DAOItems{
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    File file = new File(ConfigProperties.getInstance().getProperty("FileItem"));
    @Override
    public Item get(int id) {
        List<Item> li = getAll();
        Item t = null;
        for (Item it:li) {
            if (it.getIdItem() == id){
                t = it;
            }
        }
        return t;
    }

    @Override
    public List<Item> getAll() {
        List<Item> li = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line  != null){
                Item it = new Item(line);
                li.add(it);
                line = br.readLine();
            }


        }catch (Exception e){
            Logger.getLogger("error, when read the file item").log(Level.INFO,e.getMessage());
        }

        return li;
    }

    @Override
    public void save(Item t) {
        try(FileWriter writer = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(writer)){
            String content = t.toStringTextFile() ;
            bw.write(content);
            bw.newLine();

        }catch (IOException e){
            Logger.getLogger("error, when save the item").log(Level.INFO,e.getMessage());

        }

    }

    @Override
    public void update(Item t) {

    }

    @Override
    public void delete(Item t) {
        List<Item> li = getAll();
        li.remove(t);
        try(FileWriter writer = new FileWriter(file,false);
            BufferedWriter bw = new BufferedWriter(writer)){
            for (int i = 0; i < li.size(); i++) {
                if (i == li.size() -1){
                    String content = li.get(i).toStringTextFile();
                    bw.write(content);
                }else{
                    String content = li.get(i).toStringTextFile()+"\n";
                    bw.write(content);
                }
            }


        }catch (IOException e){
            Logger.getLogger("error, when delete the item").log(Level.INFO,e.getMessage());
        }
    }


}
