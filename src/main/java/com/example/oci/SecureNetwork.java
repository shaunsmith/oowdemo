package com.example.oci;

import com.oracle.bmc.auth.ResourcePrincipalAuthenticationDetailsProvider;
import com.oracle.bmc.core.VirtualNetworkClient;
import com.oracle.bmc.core.requests.GetVcnRequest;
import com.oracle.bmc.core.requests.ListNetworkSecurityGroupsRequest;
import com.oracle.bmc.core.responses.GetVcnResponse;
import com.oracle.bmc.core.responses.ListNetworkSecurityGroupsResponse;

public class SecureNetwork {

    public void handleRequest(String vcnId) {
        if ((null == vcnId) || (vcnId.isEmpty()) || (vcnId.isBlank())) {
            return;
        }
        System.out.println("$$$$$ Building auth provider");
        ResourcePrincipalAuthenticationDetailsProvider provider = ResourcePrincipalAuthenticationDetailsProvider
                .builder().build();
        System.out.println("$$$$$ Building VCN Client");
        VirtualNetworkClient virtualNetworkClient = VirtualNetworkClient.builder().build(provider);
        System.out.println("$$$$$ Getting VCN");
        GetVcnResponse vcnResponse = virtualNetworkClient
                .getVcn(GetVcnRequest.builder().vcnId(vcnId).build());
        System.out.println("Retrieving VCN security groups");
        ListNetworkSecurityGroupsResponse securityGroupsResponse = virtualNetworkClient
                .listNetworkSecurityGroups(
                ListNetworkSecurityGroupsRequest.builder()
                    .vcnId(vcnResponse.getVcn().getId()).build());
        System.out.println(securityGroupsResponse.getItems());
    }

}