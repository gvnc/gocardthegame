import React, { Component } from 'react';
import { Card, CardItem, Text, Body } from 'native-base';
import { Image, StyleSheet, View, Modal, TouchableHighlight, TouchableWithoutFeedback } from 'react-native';

import { connect } from "react-redux";

import apiConfig from '../../store/actions/apiConfig';

import { strings } from '../i18n';

import MCIcon from 'react-native-vector-icons/MaterialCommunityIcons';

class OpenNewCardPopup extends Component {

    render() {

        var contentComponent = null;
        if (this.props.openedCard != null) {
            contentComponent = (
                <View>
                    <TouchableWithoutFeedback onPress={() => this.props.closeModal()} >
                        <View style={{ backgroundColor: 'rgba(52, 52, 52, 0.3)', height: '100%' }}>
                            <TouchableWithoutFeedback onPress={() => { }} >
                                <Card style={styles.outerView} bordered>
                                    <CardItem bordered>
                                        <Body style={{ width: '100%', flexDirection: 'row', justifyContent: 'flex-end', alignItems: 'center' }}>
                                            <Text style={{ flex: 1, fontSize: 20, fontWeight: 'bold' }}>{strings("cards.screen." + this.props.openedCard.cardClass)} {strings('cards.screen.card')}</Text>
                                            <TouchableHighlight bordered onPress={() => this.props.closeModal()} >
                                                <MCIcon name='close-box' style={{ fontSize: 30, color: 'brown' }} />
                                            </TouchableHighlight>
                                        </Body>
                                    </CardItem>
                                    <CardItem>
                                        <Body>
                                            <Image source={{ uri: apiConfig.url + '/images/' + this.props.openedCard.imageUrl + ".jpg" }} style={styles.image} />
                                            <View style={{ width: '100%', flexDirection: 'row', justifyContent: 'flex-end', alignItems: 'center' }}>
                                                <Text style={{ flex: 1, fontSize: 30, fontWeight: 'bold' }}>{strings(this.props.openedCard.title)}</Text>
                                                <Text style={{ fontSize: 20 }}>{strings('cards.screen.points')}: {this.props.openedCard.points}</Text>
                                            </View>
                                        </Body>
                                    </CardItem>
                                </Card>
                            </TouchableWithoutFeedback>
                        </View>
                    </TouchableWithoutFeedback>
                </View>
            );
        }
        return (
            <Modal
                animationType="slide"
                transparent={true}
                visible={this.props.visible}>
                {contentComponent}
            </Modal>
        );
    }
}

const styles = StyleSheet.create({
    outerView: {
        backgroundColor: 'white',
        height: 360,
        marginTop: 150,
        marginLeft: 20,
        marginRight: 20
    },
    image: {
        width: 280,
        height: 210
    }
});

const mapStateToProps = state => {
    return {
        openedCard: state.card.openedCard,
    };
};

export default connect(mapStateToProps, null)(OpenNewCardPopup);