package com.example.contratistacompose.utils

import com.example.contratistacompose.data.source.web.models.Address


fun Address.getAddress() = "$street, " +
        "${
            if (interior.isEmpty())
                "$EXTERIOR $exterior"
            else "$INTERIOR $interior, " +
                    "$EXTERIOR $exterior"
        }, " +
        "$zip, $neighborhood, " +
        "$city, ${municipality}, " +
        "$state, $country"
