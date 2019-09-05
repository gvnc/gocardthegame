import React, { Component } from 'react';
import { Card, CardItem, Text, Left, Body} from 'native-base';
import { ActivityIndicator, StyleSheet, View, Modal, TouchableHighlight, TouchableWithoutFeedback} from 'react-native';

import { Image } from "react-native-expo-image-cache";
import { connect } from "react-redux";

import apiConfig from '../../store/actions/apiConfig';

import MCIcon from 'react-native-vector-icons/MaterialCommunityIcons';

import { strings } from '../i18n';
import AppStyles from '../AppStyles';

class AchievementPopup extends Component {

    render() {
        let selectedAchievement = {
            title: "",
            description: "",
            capImageUrl: "",
            rareCardsAwarded: 0,
            uniqueCardsAwarded: 0
        };

        let achievementId = this.props.achievementId;
        let filteredAchievements = this.props.allAchievements.filter(function (achievement) {
            return achievement.id === achievementId;
        });

        if (filteredAchievements.length > 0) {
            selectedAchievement = filteredAchievements[0];
        }

        let achievementDoneRender = null;
        if (this.props.isLoading == true) {
            achievementDoneRender = (
                <Body style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
                    <ActivityIndicator animating={true} size="small" color="#2f170e" />
                </Body>
            );
        } else {
            if (this.props.accountAchievement !== null && this.props.accountAchievement.achievement != null) {
                achievementDoneRender = (
                    <Left>
                        <Text style={{ fontWeight: 'bold' }}> {strings('profile.achievementCompleted')}</Text>
                        <MCIcon name='checkbox-marked' style={{ fontSize: 30, color: 'green', marginLeft: 25 }} />
                    </Left>
                );
            } else {
                achievementDoneRender = (
                    <Left>
                        <Text style={{ fontWeight: 'bold', color:'brown' }}> {strings('profile.achievementInProgress')}</Text>
                    </Left>
                );
            }
        }

        return (
            <Modal
                animationType="slide"
                transparent={true}
                visible={this.props.visible}
                onRequestClose={() => console.log('do nothing')}>

                <View>
                    <TouchableWithoutFeedback onPress={() => this.props.setVisible(false)} >
                    <View style={{ backgroundColor: 'rgba(52, 52, 52, 0.9)', height: '100%', justifyContent:'center'}}>
                    <TouchableWithoutFeedback onPress={() => {}} >
                        <Card style={styles.outerView} bordered>
                            <CardItem bordered style={{ backgroundColor: AppStyles.primaryColor}} >
                                <Body style={{ width: '100%', flexDirection: 'row', justifyContent: 'flex-end', alignItems: 'center' }}>
                                    <Text style={{ flex: 1, fontSize: 20, fontWeight: 'bold', color: AppStyles.panelBackground }}>
                                        {strings(selectedAchievement.title)}
                                    </Text>
                                    <TouchableHighlight bordered onPress={() => this.props.setVisible(false)} >
                                        <MCIcon name='close-box' style={{ fontSize: 30, color: AppStyles.panelBackground }} />
                                    </TouchableHighlight>
                                </Body>
                            </CardItem>
                            <CardItem bordered flex={1}>
                                <Body style={{ flex: 1, flexDirection: 'row', justifyContent: 'flex-start', alignItems: 'flex-start' }}>
                                    <Image uri={  apiConfig.url + '/images/' + selectedAchievement.capImageUrl } style={styles.image} />
                                    <View style={{ flex: 1, justifyContent: 'flex-start', alignItems: 'flex-start', marginLeft: 10 }}>
                                        <Text>{strings(selectedAchievement.description)}</Text>
                                        <Text></Text>
                                        <Text style={{ fontWeight: 'bold' }}>{selectedAchievement.rareCardsAwarded} {strings('cards.screen.Rare')}</Text>
                                        <Text style={{ fontWeight: 'bold' }}>{selectedAchievement.uniqueCardsAwarded} {strings('cards.screen.Unique')}</Text>
                                    </View>
                                </Body>
                            </CardItem>
                            <CardItem bordered>
                                {achievementDoneRender}
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
        backgroundColor: AppStyles.panelBackground,
        height: 260,
        marginLeft: 20,
        marginRight: 20
    },
    image: {
        width: 120,
        height: 144
    }
});

const mapStateToProps = state => {
    return {
        allAchievements: state.profile.allAchievements,
        accountAchievement: state.profile.accountAchievement,
        isLoading: state.uiLoading.isAchievementDone
    };
};

export default connect(mapStateToProps, null)(AchievementPopup);