package com.oyk.product.community.dao;

import com.oyk.product.community.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
