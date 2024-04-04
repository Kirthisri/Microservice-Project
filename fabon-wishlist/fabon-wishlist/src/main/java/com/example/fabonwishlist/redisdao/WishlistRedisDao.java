package com.example.fabonwishlist.redisdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.fabonwishlist.dto.FabonUserWishlist;


@Transactional
@Repository
public class WishlistRedisDao {

    public static final String HASH_KEY = "Wishlist";

    @Autowired
    private RedisTemplate template;

    public FabonUserWishlist save(FabonUserWishlist wishlist){
        template.opsForHash().put(HASH_KEY,wishlist.getWishlistProductId(),wishlist);
        return wishlist;
    }

    public List<FabonUserWishlist> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }

    public FabonUserWishlist findProductById(String id){
        System.out.println("called findProductById() from DB");
        return (FabonUserWishlist) template.opsForHash().get(HASH_KEY,id);
    }


    public String deleteProduct(String id){
         template.opsForHash().delete(HASH_KEY,id);
        return "product removed !!";
    }
    
    public String updateProduct(FabonUserWishlist wishlist){
        template.opsForHash().put(HASH_KEY,wishlist.getWishlistProductId(),wishlist);
       return "product updated !!";
   }
}
