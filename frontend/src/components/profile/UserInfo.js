import React, { Component } from 'react';

import FIcon from 'react-native-vector-icons/FontAwesome';
import { Left, Card, CardItem, Body, Button, Text } from 'native-base';
import { connect } from "react-redux";
import { loginWithFacebook } from '../../store/actions/authActions';
import { strings } from '../i18n';
import AppStyles from '../AppStyles';
import {StyleSheet} from "react-native";
import DismissKeyboard from 'dismissKeyboard';

class UserInfo extends Component {
	render() {
		var userInfoComponent = null;
		if (this.props.fbValidated == true) {
            userInfoComponent = (
                <Body>
                <Text style={styles.itemStyleLabel1}>{this.props.fullname}</Text>
                <Text style={styles.itemStyleLabel2}>{this.props.email}</Text>
                </Body>
            );
		} else {
            userInfoComponent = (
                <Button block style={{ alignSelf: 'center', marginLeft:25, backgroundColor:AppStyles.secondaryColor }} onPress={this.authFacebookHandler} >
                    <Text uppercase={false} >{strings('app.signUp')}</Text>
                </Button>
            );
		}

		return (
			<Card transparent style={{ marginLeft: 15, marginRight: 15, backgroundColor: 'rgba(52, 52, 52, 0)'}}>
				<CardItem style={{ backgroundColor: 'rgba(52, 52, 52, 0)' }}>
					<Left>
						<FIcon name='user-circle' size={70} style={{ color: AppStyles.primaryColor }} />
						{userInfoComponent}
					</Left>
				</CardItem>
			</Card>
		);
	}

    authFacebookHandler = () => {
        DismissKeyboard();
        this.props.onTryAuthFacebook();
    };
}

const styles = StyleSheet.create({
    itemStyleLabel1: {
        fontSize: 20,
        fontWeight: "bold"
    },
    itemStyleLabel2: {
        fontSize: 14,
        fontWeight: "bold",
		marginTop:10
    }
});

const mapStateToProps = state => {
	return {
		email: state.auth.email,
		fullname: state.auth.fullname,
        fbValidated: state.auth.fbValidated
	};
};

const mapDispatchToProps = dispatch => {
    return {
        onTryAuthFacebook: () => dispatch(loginWithFacebook())
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(UserInfo);