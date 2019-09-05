import React, { Component } from 'react';
import {Card, CardItem, Text} from 'native-base';
import AppStyles from "../AppStyles";
import {StyleSheet} from "react-native";

export default class TabHeader extends Component {

    render() {
        return (
            <Card transparent={true} style={{marginTop:1, paddingLeft:0, paddingRight:0, paddingTop:0, paddingBottom:0}}>
                <CardItem style={{ justifyContent: 'center', alignItems: 'center',
                    paddingTop:10, paddingBottom:10, backgroundColor: AppStyles.primaryColor }}>
                    <Text style={styles.header}>{this.props.title}</Text>
                </CardItem>
            </Card>
        );
    }
}

const styles = StyleSheet.create({
    header: {
        fontWeight: 'bold',
        fontSize: 20,
        color: '#FFFFFF'
    }
});