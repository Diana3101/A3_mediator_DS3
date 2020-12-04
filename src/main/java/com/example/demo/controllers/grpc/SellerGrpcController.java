package com.example.demo.controllers.grpc;

import com.example.seller.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class SellerGrpcController extends sellerServiceGrpc.sellerServiceImplBase {
    String url = "sellers-service";

    @Override
    public void getSellers(GetRequestSeller request, StreamObserver<GetResponseSeller> responseStreamObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 9093)
                .usePlaintext()
                .build();
        sellerServiceGrpc.sellerServiceBlockingStub stub = sellerServiceGrpc.newBlockingStub(channel);
        GetResponseSeller response = stub.getSellers(request);
        channel.shutdown();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }

    @Override
    public void getThings(GetRequestThing request, StreamObserver<GetResponseThing> responseStreamObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 9093)
                .usePlaintext()
                .build();
        sellerServiceGrpc.sellerServiceBlockingStub stub = sellerServiceGrpc.newBlockingStub(channel);
        GetResponseThing response = stub.getThings(request);
        channel.shutdown();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }

    @Override
    public void create(CreateRequest request, StreamObserver<CreateResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 9093)
                .usePlaintext()
                .build();
        sellerServiceGrpc.sellerServiceBlockingStub stub = sellerServiceGrpc.newBlockingStub(channel);
        CreateResponse response = stub.create(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

