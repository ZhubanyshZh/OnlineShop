package org.example;

import java.util.ArrayList;

public class Feedback {
    public String feedback;
    public Feedback(){
        this.feedback = "";
    }
    public Feedback(String feedback){
        this.feedback = feedback;
    }
//    public void setFeedback(String feedback){
//        this.feedback = feedback;
//    }
//    public ArrayList<Feedback> getFeedbackById(Shop shop, int id){
//        for(Product p: shop.getProducts()){
//            if(p.getId() == id){
//                return p.getFeedbacks();
//            }
//        }
//        return null;
//    }

    public String getFeedback() {
        return this.feedback;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedback='" + feedback + '\'' +
                '}';
    }
}