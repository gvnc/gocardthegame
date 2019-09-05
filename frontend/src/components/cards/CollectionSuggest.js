import React, { Component } from 'react';
import { View } from 'react-native';
import { Text, Button, Card, CardItem, Body } from 'native-base';

import FIcon from 'react-native-vector-icons/FontAwesome';
import NavigationService from '../../store/actions/NavigationService';

import { strings } from '../i18n';
import AppStyles from '../AppStyles';

export default class CollectionSuggest extends Component {

    render() {
        return (
            <View style={{ flex: 1, justifyContent: 'center'}} >
                <Card style={{ marginLeft: 25, marginRight: 25, backgroundColor: 'rgba(52, 52, 52, 0)'}}>
                    <CardItem header bordered style={{justifyContent:'center', backgroundColor: AppStyles.primaryColor }}>
                        <Text style={{fontSize: 20, color:'white' }}>{strings("cards.noSelectedCollection.title")}</Text>
                    </CardItem>
                    <CardItem bordered style={{ backgroundColor: 'rgba(52, 52, 52, 0)'}}>
                        <Body>
                            <Text style={{ fontSize: 20, marginLeft: 15, marginTop: 15, alignSelf:"center" }}>{strings("cards.noSelectedCollection.text1")}</Text>
                            <Text style={{ fontSize: 20, marginLeft: 15, marginTop: 15, alignSelf:"center" }}>{strings("cards.noSelectedCollection.text2")}</Text>
                            <FIcon name='hand-o-down' style={{ fontSize: 45, color: AppStyles.primaryColor, marginTop: 20, alignSelf: 'center' }} />
                            <Button onPress={() => NavigationService.navigate("CollectionTab")} style={{alignSelf: 'center', marginTop: 20 }} >
                                <Text uppercase={false} >{strings("cards.noSelectedCollection.gotoCollections")}</Text>
                            </Button>
                            <Text style={{ fontSize: 20, marginLeft: 15, marginTop: 15 }}></Text>
                        </Body>
                    </CardItem>
                </Card>
            </View>
        );
    }
}