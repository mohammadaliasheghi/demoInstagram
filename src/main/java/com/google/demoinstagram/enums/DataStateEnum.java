package com.google.demoinstagram.enums;

import lombok.Getter;

@Getter
public enum DataStateEnum {

    ZERO(0) {
        @Override
        public Integer getTitle() {
            return DataStateEnum.ZERO.value;
        }
    },
    HUNDRED(100) {
        @Override
        public Integer getTitle() {
            return DataStateEnum.HUNDRED.value;
        }
    };

    private final Integer value;

    DataStateEnum(final Integer value) {
        this.value = value;
    }

    public abstract Integer getTitle();
}
