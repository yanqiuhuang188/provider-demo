package com.nimble.provider.controller;


import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaEventListener;
import com.nimble.provider.entity.User;
import com.nimble.provider.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProviderController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/provid/{id}")
    private User findById(@PathVariable Long id){
        return this.userRepository.getOne(id);
    }

    @GetMapping("/offline/{port}")
    public synchronized void offline(@PathVariable Long port){
////        DiscoveryManager.getInstance().shutdownComponent();
//        String applicationName = null;
//        Integer index = null;
//        List<String> services = discoveryClient.getServices();
//        for(String s :  services){
//            List<ServiceInstance> serviceInstances =  discoveryClient.getInstances(s);
//            for(ServiceInstance si : serviceInstances){
//                int myPort = si.getPort();
//                System.out.println(si);
//                if(port == myPort){
//                    index = serviceInstances.indexOf(si);
//                    applicationName = si.getServiceId();
//                    System.out.println("***0" + si.getUri());
//                }
//            }
//        }

        this.getRegistered();

        List<ServiceInstance> consumer1 = discoveryClient.getInstances("CONSUMER");
//        ServiceInstance consumer = consumer1.remove(1);
        consumer1.clear();

        this.getRegistered();


    }

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient discove2ryClient;

    @RequestMapping("/test")
    public void test(){
//        discove2ryClient.shutdown();
        discove2ryClient.getAllKnownRegions();

        discove2ryClient.registerEventListener(new test());
    }



    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping("/registered")
    public String getRegistered(){
        List<ServiceInstance> list = discoveryClient.getInstances("PROVIDER");
        System.out.println(discoveryClient.getServices());
        System.out.println("discoveryClient.getServices().size() = " + discoveryClient.getServices().size());

        for( String s :  discoveryClient.getServices()){
            System.out.println("services " + s);
            List<ServiceInstance> serviceInstances =  discoveryClient.getInstances(s);
            for(ServiceInstance si : serviceInstances){
                System.out.println("    services:" + s + ":getHost()=" + si.getHost());
                System.out.println("    services:" + s + ":getPort()=" + si.getPort());
                System.out.println("    services:" + s + ":getServiceId()=" + si.getServiceId());
                System.out.println("    services:" + s + ":getUri()=" + si.getUri());
                System.out.println("    services:" + s + ":getMetadata()=" + si.getMetadata());
            }

        }

        if (list != null && list.size() > 0 ) {
            for(ServiceInstance instance : list){
                System.out.println(instance.getUri());
            }
        }
        return null;


    }

}
