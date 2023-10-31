package com.xpanxion.solution;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import com.xpanxion.java.assignments.DataAccess;
import com.xpanxion.java.assignments.model.Cat;
import com.xpanxion.java.assignments.model.Department;
import com.xpanxion.java.assignments.model.Product;
import com.xpanxion.java.assignments.model.Person;
import com.xpanxion.java.assignments.model.PersonCat;


public class Worker {
    public void ex1 () {
       var produce = DataAccess.getProducts();
       var depart = DataAccess.getDepartments();
       Map<Integer, String> department = new HashMap<Integer, String>(depart.size());
       for(Department d : depart){
            department.put(d.getId(), d.getName());
       }
       var product = produce.stream().map(p-> new Product(p.getId(), p.getDepartmentId(), department.get(p.getDepartmentId()), p.getName(), p.getPrice(), p.getSku())).collect(Collectors.toList());
       for(Product p: product){
            System.out.println(p);
       }
    }

    public void ex2 (){
        var produce = DataAccess.getProducts();
        var product = produce.stream().map(p-> new Product(p.getId(), p.getDepartmentId(), "N/A", p.getName(), p.getPrice(), p.getSku())).collect(Collectors.toList());
        for(Product p: product){
            System.out.println(p);
        }
    }

    public void ex3(){
        var produce = DataAccess.getProducts();
        var product = produce.stream().filter(p -> p.getDepartmentId() == 1 && p.getPrice() >= 10).collect(Collectors.toList());
        for(Product p: product){
            System.out.println(p);
        }
    }

    public void ex4(){
        var produce = DataAccess.getProducts();
        var depart = DataAccess.getDepartments();
        Map<Integer, String> department = new HashMap<Integer, String>(depart.size());
        for(Department d : depart){
            department.put(d.getId(), d.getName());
        }
        var price = produce.stream().map(p-> new Product(p.getId(), p.getDepartmentId(), department.get(p.getDepartmentId()), p.getName(), p.getPrice(), p.getSku())).filter(p -> p.getDepartmentName() == "Food").mapToDouble(p -> p.getPrice()).sum();
        System.out.println("$"+price);
    }
    public void ex5(){
        var person = DataAccess.getPeople();
        var people = person.stream().filter(p -> p.getId() <= 3).map(p -> new Person(p.getId(), p.getFirstName(), p.getLastName(), p.getAge(), p.getSsn().substring(7))).collect(Collectors.toList());
        for(Person p: people){
            System.out.println(p);
        }
    }
    public void ex6(){
        var cats = DataAccess.getCats();
        cats.sort(Comparator.comparing(Cat::getName));
        for(Cat c: cats){
            System.out.println(c);
        }

    }
    public void ex7(){
        String[] words = DataAccess.getWords().split(" ");
        HashMap<String, Integer> countWords = new HashMap<String, Integer>();
        for(String w: words){
            if(countWords.containsKey(w)){
                countWords.put(w, countWords.get(w) + 1);
            }
            else{
                countWords.put(w, 1);
            }
        }
        for(String w: countWords.keySet()){
            System.out.println( w + " = " + countWords.get(w));
        }
    }

    public void ex8(){
        var person = DataAccess.getPeople();
        var people = person.stream().map(p -> new Person(p.getId(), p.getFirstName(), null, 0,null)).collect(Collectors.toList());
        for(Person p: people){
            System.out.println(p);
        }
    }

    public void ex9(){
        var produce = DataAccess.getProducts();
        var depart = DataAccess.getDepartments();
        Map<Integer, String> department = new HashMap<Integer, String>(depart.size());
        for(Department d : depart){
            department.put(d.getId(), d.getName());
        }
        var product = produce.stream().map(p-> new Product(p.getId(), p.getDepartmentId(), department.get(p.getDepartmentId()), p.getName(), p.getPrice(), p.getSku())).collect(Collectors.toList());
        var tarriff = product.stream().filter(p -> p.getDepartmentName().equals("Electronics")).mapToDouble(p -> p.getPrice() + 2).sum();
        System.out.println("$"+ tarriff);
    }

    public void ex10(){
        var person = DataAccess.getPeople();
        var cats = DataAccess.getCats();
        var personCat = person.stream().map(p -> new PersonCat(p.getId(), p.getFirstName(), cats.stream().filter(c -> c.getId() == p.getId()).collect(Collectors.toList()))).collect(Collectors.toList());
        for(PersonCat pc: personCat){
            System.out.println(pc);
        }
    }
}
