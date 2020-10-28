package ru.armishev.dao.user;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armishev.dao.RestClient;

@Service
public class OrderDAOUser implements OrderRepositoryUser {
    @Autowired
    private CartDAOUser dao_cart;

    @Autowired
    private RestClient restClient;
    private String main_url = "/order/";

    @Override
    public String createOrder() {
        String jsonCart = getJsonCart();

        return null;
    }

    private String getJsonCart() {
        JsonObject order_api =  new JsonObject();

        order_api.addProperty("user_id", 1);

        JsonArray product_array = new JsonArray();
        if (!dao_cart.getProducts().isEmpty()) {
            for(CartDAOUser.CartProduct cartProduct : dao_cart.getProducts()) {
                JsonObject elem = new JsonObject();
                elem.addProperty("id", cartProduct.getProduct().getId());
                elem.addProperty("quantity", cartProduct.getQuantity());

                product_array.add(elem);
            }
        }
        order_api.addProperty("products", product_array.toString());

        String jsonArray = restClient.put(main_url, order_api.toString());

        return jsonArray;
    }
}
