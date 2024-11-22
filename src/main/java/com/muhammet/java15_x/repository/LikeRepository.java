package com.muhammet.java15_x.repository;

import com.muhammet.java15_x.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    /**
     * Bir post u bir kullanıcının like yapıp yapmadığı bilgisi için kullanıyoruz.
     * burada like datası dönülür, data hiç yok se zaten like yapmamıştır
     * ancak like datası dönebilir like içerisinde state e bakılmalıdır çünkü
     * kullanıcı daha önce like yapmış sonra geri almış olabilir.
     * @param userId
     * @param postId
     * @return
     */
    Optional<Like> findOptionalByUserIdAndPostId(Long userId, Long postId);
}
