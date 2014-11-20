/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.alerts.transport.actions.ack;

import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.alerts.AlertAckState;
import org.elasticsearch.common.Nullable;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;

import java.io.IOException;

/**
 */
public class AckAlertResponse extends ActionResponse {

    private AlertAckState alertAckState;

    public AckAlertResponse() {
    }

    public AckAlertResponse(@Nullable AlertAckState alertAckState) {
        this.alertAckState = alertAckState;
    }

    public AlertAckState getAlertAckState() {
        return alertAckState;
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        if (in.readBoolean()) {
            AlertAckState.fromString(in.readString());
        }
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeBoolean(alertAckState != null);
        if (alertAckState != null) {
            out.writeString(alertAckState.toString());
        }
    }
}
