package com.bjit.raihan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String name;

    @Min(value = 0)
    private double price;

    @Builder.Default
    private Types type = Types.SubItem;

    // @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum Types {
        MainItem("Main Item"),
        SubItem("Sub Item");

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
