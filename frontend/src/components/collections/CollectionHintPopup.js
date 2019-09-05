import React, { Component } from 'react';
import {Body, Button, Card, CardItem, Text} from 'native-base';
import { Modal, StyleSheet, TouchableHighlight, TouchableWithoutFeedback, View} from 'react-native';

import { strings } from '../i18n';
import AppStyles from '../AppStyles';

import FIcon from 'react-native-vector-icons/FontAwesome';
import MCIcon from 'react-native-vector-icons/MaterialCommunityIcons';
import NavigationService from "../../store/actions/NavigationService";

export default class CollectionHintPopup extends Component {

    render() {

        return (
            <Modal
                animationType="slide"
                transparent={true}
                visible={this.props.visible}
                onRequestClose={() => console.log('do nothing')}>

                <View>
                    <TouchableWithoutFeedback>
                        <View style={{ backgroundColor: 'rgba(52, 52, 52, 0.9)', height: '100%', justifyContent:'center' }}>
                            <TouchableWithoutFeedback onPress={() => { }}>
                                <Card style={styles.outerView} bordered>
                                    <CardItem bordered style={{ backgroundColor: AppStyles.primaryColor}} >
                                        <Body style={{
                                            width: '100%',
                                            flexDirection: 'row',
                                            justifyContent: 'flex-end',
                                            alignItems: 'center'
                                        }}>
                                            <Text style={{
                                                flex: 1,
                                                fontSize: 20,
                                                fontWeight: 'bold',
                                                color: AppStyles.panelBackground
                                            }}>{strings('collections.hint.title')}</Text>
                                        </Body>
                                    </CardItem>
                                    <CardItem style={{flex:1, flexDirection: 'column'}} >
                                        <Body>
                                            <Text style={{ fontSize: 20, marginLeft: 15, marginTop: 15, alignSelf:"center"  }}>{strings("collections.hint.text")}</Text>
                                            <FIcon name='hand-o-down' style={{ fontSize: 45, color: AppStyles.primaryColor, marginTop: 20, alignSelf: 'center' }} />
                                            <Button onPress={() => {this.props.setVisible(false);NavigationService.navigate("CardTab")}} style={{alignSelf: 'center', marginTop: 20 }} >
                                                <Text uppercase={false} >{strings("collections.hint.gotoCards")}</Text>
                                            </Button>
                                            <Text style={{ fontSize: 20, marginLeft: 15, marginTop: 15 }}></Text>
                                        </Body>
                                    </CardItem>
                                </Card>
                            </TouchableWithoutFeedback>
                        </View>
                    </TouchableWithoutFeedback>
                </View>
            </Modal>
        );
    }
}

const styles = StyleSheet.create({
    outerView: {
        flexDirection: 'column',
        height: 260,
        width: undefined,
        marginLeft: 20,
        marginRight: 20
    },
    infoKey: {
        fontWeight: 'bold',
        width:125
    },
    infoValue: {
        fontWeight: 'normal',
        width:150,
        color:"#147090"
    }
});