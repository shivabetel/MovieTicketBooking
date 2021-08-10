package com.ssorg.booking.constants;

import com.ssorg.booking.mockdatafactory.CreateTheatre;
import com.ssorg.booking.mockdatafactory.Theatre1;
import com.ssorg.booking.mockdatafactory.Theatre2;
import com.ssorg.booking.mockdatafactory.Theatre3;

import java.util.function.Supplier;

public enum  Theatres {
    THEATRE1(Theatre1::new, "theatre1"),
    THEATRE2(Theatre2::new, "theatre2"),
    THEATRE3(Theatre3::new, "theatre3");


    private Supplier<CreateTheatre> constructor;

    public String getTheatreName() {
        return theatreName;
    }

    private String theatreName;

    Theatres(Supplier<CreateTheatre> constructor, String theatreName) {
        this.constructor = constructor;
        this.theatreName = theatreName;
    }

    public Supplier<CreateTheatre> getConstructor() {
        return constructor;
    }
}
