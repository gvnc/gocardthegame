import React, { Component } from 'react';
import { Container, Content } from 'native-base';
import { ImageBackground, View, Platform} from "react-native";
import UserInfo from './UserInfo';
import GameStats from './GameStats';
import Achievements from './Achievements';
import texture from "../../assets/images/texture2.jpg";
import { AdMobBanner} from 'expo';
import apiConfig from '../../store/actions/apiConfig';
import TabHeader from "../ui/TabHeader";
import {strings} from "../i18n";

export default class TabProfileScreen extends Component {

    render() {
        let bannerId=apiConfig.androidBannerId;
        if(Platform.OS === 'ios') {
            bannerId=apiConfig.iosBannerId;
        }else{
            bannerId=apiConfig.androidBannerId;
        }
        return (
            <Container>
                <ImageBackground source={texture} style={{ width: "100%", flex: 1 }}>
                    <TabHeader title={strings("tabBar.profile")} />
                    <UserInfo />
                    <GameStats />
                    <Achievements />
                    <View style={{flex:1, alignItems: 'center', marginTop: 5 }}>
                        <AdMobBanner bannerSize="banner" adUnitID={bannerId}/>
                    </View>
                </ImageBackground>
            </Container>
        );
    }
}
