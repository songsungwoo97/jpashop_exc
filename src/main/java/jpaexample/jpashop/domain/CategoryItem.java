package jpaexample.jpashop.domain;

import jpaexample.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "category_item")
@Getter @Setter
public class CategoryItem {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}
