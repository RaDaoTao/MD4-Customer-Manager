package rikkei.academy.service;

import rikkei.academy.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService implements IGenericService<Customer,Integer>{

    public CustomerService() {
    }

    @Override
    public List<Customer> findAll() {

    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public Customer findById(Integer integer) {


    }
    public int getNewId(){
        int idMax = 0;
        for (Customer c:customers
             ) {
            if(c.getId()>idMax){
                idMax= c.getId();
            }
        }
        return idMax+1;
    }
    @Override
    public void deleteById(Integer integer){

    }
}
