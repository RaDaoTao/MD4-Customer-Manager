package rikkei.academy.service;

import rikkei.academy.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService implements IGenericService<Customer,Integer>{
    private List<Customer> customers;

    public CustomerService() {
        customers = new ArrayList<>();
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public void save(Customer customer) {
        if(findById(customer.getId())==null){
            // thêm mới
            customers.add(customer);
        }else {
            // sửa
            customers.set(customers.indexOf(findById(customer.getId())), customer);
        }
    }

    @Override
    public Customer findById(Integer integer) {
        for (Customer c:customers) {
            if(c.getId()==integer){
                return c;
            }
        }
        return null;
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
        customers.remove(findById(integer));
    }
}
