package com.springsecurity.Asst.customerserviceImpl;

import com.springsecurity.Asst.customerservice.CustomerService;
import com.springsecurity.Asst.entity.Customer;
import com.springsecurity.Asst.entity.Userinfo;
import com.springsecurity.Asst.repository.CustomerRepository;
import com.springsecurity.Asst.repository.UserinfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserinfoRepository userinfoRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer saveorUpdateCustomer(Customer customer) {

        Optional<Customer> byId = customerRepository.findById(customer.getId());
        if(byId.isPresent())
        {
            Customer customer1 = byId.get();
            BeanUtils.copyProperties(customer1,customer);
            return customerRepository.save(customer);
        }

        return customerRepository.save(customer);
    }

    @Override
    public String addUser(Userinfo userInfo) {

        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userinfoRepository.save(userInfo);
        return "user added to system ";
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        if(customer.getId()==0)
            return null;

        Optional<Customer> byId = customerRepository.findById(customer.getId());
        Customer customer1=null;
        if(byId.isPresent())
        {
             customer1 = byId.get();
            BeanUtils.copyProperties(customer,customer1);
            return customerRepository.save(customer);
        }
            return null;

    }

    @Override

    public String   deleteCustomer(Customer customer) {
        if(customer.getId()==0)
            return null;

        Optional<Customer> byId = customerRepository.findById(customer.getId());
        Customer customer1=null;
        if(byId.isPresent())
        {
            customer1 = byId.get();
            BeanUtils.copyProperties(customer,customer1);
            customerRepository.delete(customer);
            return "Deleted Sucessfully";
        }
        return null;
    }


}

