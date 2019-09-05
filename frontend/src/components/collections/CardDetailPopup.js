import React, { Component } from 'react';
import { Body, Card, CardItem, Text, Left, Right } from 'native-base';
import {
    Modal,
    StyleSheet,
    TouchableHighlight,
    TouchableWithoutFeedback,
    View
} from 'react-native';

import { Image } from "react-native-expo-image-cache";
import apiConfig from '../../store/actions/apiConfig';

import { strings } from '../i18n';
import AppStyles from '../AppStyles';

import MCIcon from 'react-native-vector-icons/MaterialCommunityIcons';

export default class CardDetailPopup extends Component {

    render() {
        var wWidth = this.props.windowWidth + 1;
        var wHeight = this.props.windowHeight + 1;

        return (
            <Modal
                animationType="fade"
                transparent={true}
                visible={this.props.visible}
                onRequestClose={() => {}}>

                    <TouchableWithoutFeedback onPress={() => this.props.setVisible(false)}>
                        <View style={{ backgroundColor: 'rgba(52, 52, 52, 0.9)', width:wWidth, height:wHeight, justifyContent:'center', alignItems:"center" }}>
                            <TouchableWithoutFeedback onPress={() => { }}>
                                <Card style={{ marginLeft: 20, marginRight: 20}} bordered>
                                    <CardItem bordered style={{ backgroundColor: AppStyles.primaryColor}} >
                                        <Body style={{width: '100%', flexDirection: 'row', justifyContent: 'flex-end', alignItems: 'center' }}>
                                            <Text style={{ flex: 1, fontSize: 20, fontWeight: 'bold', color: AppStyles.panelBackground}}>{strings(this.props.title)}</Text>
                                            <TouchableHighlight bordered onPress={() => this.props.setVisible(false)}>
                                                <MCIcon name='close-box' style={{ fontSize: 30, color: AppStyles.panelBackground }} />
                                            </TouchableHighlight>
                                        </Body>
                                    </CardItem>
                                    <CardItem>
                                        <Image uri={apiConfig.url + '/images/' + this.props.imageUrl + ".jpg"} style={{width:"100%", aspectRatio: 1.33}} resizeMode="cover"/>
                                    </CardItem>
                                    <CardItem>
                                        <Left>
                                            <Left>
                                                <Text style={{ fontWeight: 'bold' }}>{strings('cards.screen.points')}</Text>
                                                <Text style={{ fontWeight: 'normal', color:AppStyles.secondaryColor }}>{this.props.points}</Text>
                                            </Left>
                                            <Right>
                                                <Text style={{ fontWeight: 'bold'}}>{strings('cards.screen.class')}</Text>
                                                <Text style={{ fontWeight: 'normal', color:AppStyles.secondaryColor }}>{strings("cards.screen." + this.props.cardClass)}</Text>
                                            </Right>
                                        </Left>
                                    </CardItem>
                                </Card>
                            </TouchableWithoutFeedback>
                        </View>
                    </TouchableWithoutFeedback>
            </Modal>
        );
    }
}