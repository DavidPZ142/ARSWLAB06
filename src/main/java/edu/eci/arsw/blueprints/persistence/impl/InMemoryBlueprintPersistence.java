/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author hcadavid
 */
@Repository
@Qualifier("Memory")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new ConcurrentHashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Point[] pts1 =new Point[]{new Point(160, 120),new Point(100, 121)};
        Point[] pts2 =new Point[]{new Point(134, 98),new Point(21, 34)};
        Blueprint bp=new Blueprint("Nicolas",  "EdificioG ",pts);
        Blueprint bp1 = new Blueprint("David", "EdificioE",pts1);
        Blueprint bp2 = new Blueprint("David", "EdificioF ",pts1);
        Blueprint bp3 = new Blueprint("DaVinci", "Obelisco",pts1);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        blueprints.put(new Tuple<>(bp1.getAuthor(),bp1.getName()), bp1);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);
        
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> hs = new HashSet<>();
        for(Tuple<String,String> i : blueprints.keySet()){
            if(i.o1.equals(author)){
                hs.add(blueprints.get(i));
            }
        }

        return hs;
    }

    @Override
    public Set<Blueprint> getAllBluePrints(){
        Set<Blueprint> hs = new HashSet<>();
        for(Tuple<String,String> i : blueprints.keySet()){
            hs.add(blueprints.get(i));
        }
        return hs;
    }

    @Override
    public void deleteBlueprint(String author, String name){
        blueprints.remove(new Tuple<>(author, name));
    }


}
