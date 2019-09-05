import React, { Component } from 'react';
import { Asset, Font, AppLoading } from "expo";
import NavigationService from './src/store/actions/NavigationService';
import RootStack from './src/components/AppNavigator';
import AppIntroSlider from 'react-native-app-intro-slider';
import { StyleSheet } from 'react-native';
import { strings } from './src/components/i18n';
import AppStyles from './src/components/AppStyles';
import I18n from 'ex-react-native-i18n';
import {setOpeningIntro} from "./src/store/actions/authActions";
import connect from "react-redux/es/connect/connect";

class Main extends Component {

    constructor(props) {
        super(props);
        this.state = {
            loading: true
        };
    }

    _onDone = () => {
        this.props.setOpeningIntro(true);
    }

    async componentWillMount() {

        await Font.loadAsync({
            Roboto: require("native-base/Fonts/Roboto.ttf"),
            Roboto_medium: require("native-base/Fonts/Roboto_medium.ttf")
        });

        await Asset.loadAsync([
            require('./src/assets/images/logo.png'),
            require('./src/assets/images/texture1.jpg'),
            require('./src/assets/images/texture2.jpg'),
            require('./src/assets/images/intro/cards.png'),
            require('./src/assets/images/intro/dailyCards.png'),
            require('./src/assets/images/intro/collections.png'),
        ])

        await I18n.initAsync();

        this.setState({
            ...this.state,
            loading: false
        });
    }

    render() {
        if (this.state.loading) {
            return <AppLoading/>;
        }
        const slidesUpdated = [
            {
                ...slides[0],
                title: strings('intro.title1'),
                text: strings('intro.text1')
            },
            {
                ...slides[1],
                title: strings('intro.title2'),
                text: strings('intro.text2')
            },
            {
                ...slides[2],
                title: strings('intro.title3'),
                text: strings('intro.text3')
            },
        ];

        if (this.props.displayedOpeningIntro) {
            return <RootStack ref={navigatorRef => { NavigationService.setTopLevelNavigator(navigatorRef) }} />;
        } else {
            return <AppIntroSlider slides={slidesUpdated} onDone={this._onDone} bottomButton={true}
                            nextLabel={strings('intro.nextLabel')} doneLabel={strings('intro.doneLabel')} />;
        }
    };
}


const styles = StyleSheet.create({
    image: {
        width: 320,
        height: 320,
    }
});

const slides = [
    {
        key: 'screen1',
        title: strings('intro.title1'),
        text: strings('intro.text1'),
        image: require('./src/assets/images/intro/cards.png'),
        imageStyle: styles.image,
        titleStyle: {
            color: AppStyles.intro1FontColor,
            fontSize: 25,
            fontWeight: 'bold',
        },
        textStyle: {
            color: AppStyles.intro1FontColor,
            fontSize: 20
        },
        backgroundColor: AppStyles.intro1BgColor,
    },
    {
        key: 'screen2',
        title: strings('intro.title2'),
        text: strings('intro.text2'),
        image: require('./src/assets/images/intro/dailyCards.png'),
        imageStyle: styles.image,
        titleStyle: {
            color: AppStyles.intro2FontColor,
            fontSize: 25,
            fontWeight: 'bold',
        },
        textStyle: {
            color: AppStyles.intro2FontColor,
            fontSize: 20
        },
        backgroundColor: AppStyles.intro2BgColor,
    },
    {
        key: 'screen3',
        title: strings('intro.title3'),
        text: strings('intro.text3'),
        image: require('./src/assets/images/intro/collections.png'),
        imageStyle: styles.image,
        titleStyle: {
            color: AppStyles.intro1FontColor,
            fontSize: 25,
            fontWeight: 'bold',
        },
        textStyle: {
            color: AppStyles.intro1FontColor,
            fontSize: 20
        },
        backgroundColor: AppStyles.intro1BgColor,
    }
];


const mapStateToProps = state => {
    return {
        displayedOpeningIntro: state.auth.displayedOpeningIntro
    };
};

const mapDispatchToProps = dispatch => {
    return {
        setOpeningIntro: (newValue) => dispatch(setOpeningIntro(newValue))
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(Main);