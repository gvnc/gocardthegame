import React, { Component } from 'react';
import { Body, Card, CardItem, Text, Left } from 'native-base';
import ProgressBarAnimated from 'react-native-progress-bar-animated';
import { Modal, StyleSheet, TouchableHighlight, TouchableWithoutFeedback, View} from 'react-native';

import { strings } from '../i18n';
import AppStyles from '../AppStyles';

import MCIcon from 'react-native-vector-icons/MaterialCommunityIcons';

export default class CollectionInfoPopup extends Component {

    render() {

        var categoryName = "";
        var collectionName = "";
        var numOfCards = 0;

        if(this.props.collection != null){
            var categoryName = this.props.collection.collectionCategory.title;
            var collectionName = this.props.collection.title;
            var numOfCards = this.props.collection.numOfCards;
        }

        var cardsOpened = this.props.cardsOpened;
        var cardsClosed = numOfCards - cardsOpened;
        var completion =  Math.round(100 * (cardsOpened / numOfCards));

        return (
            <Modal
                animationType="slide"
                transparent={true}
                visible={this.props.visible}
                onRequestClose={() => console.log('do nothing')}>

                <View>
                    <TouchableWithoutFeedback onPress={() => this.props.setVisible(false)}>
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
                                        }}>{strings('collections.info')}</Text>
                                        <TouchableHighlight bordered onPress={() => this.props.setVisible(false)}>
                                            <MCIcon name='close-box' style={{ fontSize: 30, color: AppStyles.panelBackground }} />
                                        </TouchableHighlight>
                                        </Body>
                                    </CardItem>
                                    <CardItem style={{flex:1, flexDirection: 'column'}} >
                                        <Left>
                                            <Text style={styles.infoKey}>{strings('collections.category')}</Text>
                                            <Text style={[styles.infoValue,{color:"#900C3F"}]}>{strings(categoryName)}</Text>
                                        </Left>
                                        <Left>
                                            <Text style={styles.infoKey}>{strings('collections.collection')}</Text>
                                            <Text style={[styles.infoValue,{color:"#900C3F"}]}>{strings(collectionName)}</Text>
                                        </Left>
                                        <Left>
                                            <Text style={styles.infoKey}>{strings('collections.totalCards')}</Text>
                                            <Text style={styles.infoValue}>{numOfCards}</Text>
                                        </Left>
                                        <Left>
                                            <Text style={styles.infoKey}>{strings('collections.openedCards')}</Text>
                                            <Text style={styles.infoValue}>{cardsOpened}</Text>
                                        </Left>
                                        <Left>
                                            <Text style={styles.infoKey}>{strings('collections.closedCards')}</Text>
                                            <Text style={styles.infoValue}>{cardsClosed}</Text>
                                        </Left>
                                        <Left>
                                            <Text style={styles.infoKey}>{strings('collections.completion')}</Text>
                                            <Text style={styles.infoValue}>{completion} %</Text>
                                        </Left>
                                        <ProgressBarAnimated
                                            width={275}
                                            value={completion}
                                            backgroundColorOnComplete="#6CC644"
                                        />
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
        height: 300,
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