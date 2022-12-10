package hello.itemservice.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity //Entity 어노테이션이 있어야 JPA가 인식한다.
/*@Table(name = "item") // 객체명과 테이블명이 같을 시에 생략가능*/
public class Item {

    //GenerationType.IDENTITY = PK 생성값을 데이터베이스에서 생성하는 방식을 사용 (MySQL의 auto increment)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //객체의 필드를 테이블의 컬럼과 매핑
    //스프링 부트와 통합해서 사용하면 카멜케이스를 스네이크케이스로 자동 변환해준다
    @Column(name = "item_name", length = 10)
    private String itemName;
    //테이블 컬럼명과 객체 필드명이 같을 시에는 Column 어노테이션 생략가능
    private Integer price;
    private Integer quantity;

    //JPA는 public 또는 protected의 기본 생성자가 필수
    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
