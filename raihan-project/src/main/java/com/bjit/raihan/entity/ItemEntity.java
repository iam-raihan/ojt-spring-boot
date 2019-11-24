package com.bjit.raihan.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Entity(name = "Items")
@Table(name = "items")
public class ItemEntity extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Min(value = 0)
    private double price;

    @Builder.Default
    @Column(nullable = false)
    private Types type = Types.SubItems;

    // @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum Types {
        MainItems("Lunch (Main Items)"),
        SubItems("Lunch (Sub Items)"),
        Snacks("Snacks");

        private String value;

        Types(final String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }
    }
}
