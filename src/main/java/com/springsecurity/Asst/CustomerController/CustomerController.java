package com.springsecurity.Asst.CustomerController;

import com.springsecurity.Asst.customerservice.CustomerService;
import com.springsecurity.Asst.entity.Customer;
import com.springsecurity.Asst.entity.Userinfo;
import com.springsecurity.Asst.request.AuthRequest;
import com.springsecurity.Asst.service.JwtService;
import org.apache.catalina.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/new")
    public String addNewUser(@RequestBody Userinfo userInfo) {
        return customerService.addUser(userInfo);
    }

    @PostMapping("/saveCustomer")

    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Customer customer1 = null;
        if (customer != null) {
            customer1 = customerService.saveorUpdateCustomer(customer);
        }
        if (customer1 != null)
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
          System.out.println("Inside Authentication dddddddddddddddddddddddd");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }





    }
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Customer> getAllCustomer()
    {
        return customerService.findAll();
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //@PreAuthorize("hasR")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer)
    {
        System.out.println("sdfsdfsdffffffff");
        Customer customer1 = null;
        if (customer != null) {
            customer1 = customerService.updateCustomer(customer);
        }
        if (customer1 != null)
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //@PreAuthorize("hasR")
    public ResponseEntity<Customer> deleteCustomer(@RequestBody Customer customer)
    {
        System.out.println("sdfsdfsdffffffff");
        String deletedcustomer1 = null;
        if (customer != null) {
            deletedcustomer1 = customerService.deleteCustomer(customer);
        }
        if (deletedcustomer1 != null)
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}