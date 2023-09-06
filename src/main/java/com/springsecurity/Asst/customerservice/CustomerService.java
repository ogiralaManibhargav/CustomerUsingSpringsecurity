package com.springsecurity.Asst.customerservice;

import com.springsecurity.Asst.entity.Customer;
import com.springsecurity.Asst.entity.Userinfo;

import java.util.List;

public interface CustomerService {
    Customer saveorUpdateCustomer(Customer customer);

    String addUser(Userinfo userInfo);

    List<Customer> findAll();

    Customer updateCustomer(Customer customer);

    String deleteCustomer(Customer customer);

    // Customer updateCustomer(Customer customer);
}
