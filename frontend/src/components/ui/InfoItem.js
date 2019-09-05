import React from "react";
import { StyleSheet } from "react-native";
import { Text, Col, Grid } from "native-base";

const infoItem = props => {

    let labelWidth=props.labelWidth;

    if(labelWidth === undefined)
        labelWidth = 150;

    return (
        <Grid>
            <Col style={{ flexDirection: 'row', justifyContent: 'flex-end', width: labelWidth}}>
                <Text style={styles.itemStyleLabel}>{props.labelValue}</Text>
            </Col>
            <Col>
                <Text style={styles.itemStyleValue}>{props.itemValue}</Text>
            </Col>
        </Grid>
    )
};

const styles = StyleSheet.create({
    itemStyleLabel: {
        fontSize: 14,
        fontWeight: "bold",
        marginLeft: 10
    },
    itemStyleValue: {
        fontSize: 14
    }
});

export default infoItem;
