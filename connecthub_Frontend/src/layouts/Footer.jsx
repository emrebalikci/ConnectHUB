import React from "react";
import {Grid, Header} from "semantic-ui-react";

export default function Footer() {
    return (
        <div className={"Footer"} style={{padding: "5em 5em", marginLeft: 90}}>
            <Grid celled={"internally"} stackable>

                <Grid.Column width={8} textAlign={"right"} verticalAlign={"middle"}>
                    <Header as="h2">
                        <Header.Content>
                            <Grid stackable>
                                <Grid stackable>
                                    <Grid.Row>
                                        <Grid.Row>
                                            <font color="black" style={{fontStyle: "italic"}}>
                                                Connect &nbsp;
                                            </font>
                                        </Grid.Row>
                                        <Grid.Row>
                                            <font color="black" style={{fontStyle: "italic"}}>
                                                HUB&nbsp;
                                            </font>
                                        </Grid.Row>
                                    </Grid.Row>
                                    <Grid.Row>
                                        <Grid.Row>
                                            <font color="black" style={{fontStyle: "italic", stackable: false}}>
                                                Hrms Project&nbsp;
                                            </font>
                                        </Grid.Row>
                                        <Grid.Row>
                                            <font color="black" style={{fontStyle: "italic"}}>
                                                &nbsp;
                                            </font>
                                        </Grid.Row>
                                    </Grid.Row>
                                </Grid>
                            </Grid>
                        </Header.Content>
                    </Header>
                </Grid.Column>

                <Grid.Column width={5} textAlign={"left"} verticalAlign={"middle"}>
                    <Header as="h2">
                        <Header.Content>
                            <font color="black" style={{fontStyle: "italic"}}>
                                <a href="emrebalikci.com">emrebalikci.com</a>
                            </font>
                        </Header.Content>
                    </Header>
                </Grid.Column>

            </Grid>
        </div>
    );
}