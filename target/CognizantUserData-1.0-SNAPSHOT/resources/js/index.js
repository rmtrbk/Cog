class CongnizantUserData extends React.Component {
  constructor(props) {
      super(props);

      this.state = {
        searchedName: '', id: '', firstName: '', lastName: '', email: '',
        addressLine1: '', addressLine2: '', town: '', postalCode: '', country: '',
        phoneNumber1: '', phoneNumber2: '', phoneNumber3: '',
        actionSuccessful: false, actionMessage: '', receivedDate: ''
      }

      this.setSearchedName = this.setSearchedName.bind(this);

      this.setId = this.setId.bind(this);
      this.setFirstName = this.setFirstName.bind(this);
      this.setLastName = this.setLastName.bind(this);
      this.setEmail = this.setEmail.bind(this);
      this.setAddressLine1 = this.setAddressLine1.bind(this);
      this.setAddressLine2 = this.setAddressLine2.bind(this);
      this.setTown = this.setTown.bind(this);
      this.setPostalCode = this.setPostalCode.bind(this);
      this.setCountry = this.setCountry.bind(this);
      this.setPhoneNumber1 = this.setPhoneNumber1.bind(this);
      this.setPhoneNumber2 = this.setPhoneNumber2.bind(this);
      this.setPhoneNumber3 = this.setPhoneNumber3.bind(this);

      this.findRecord = this.findRecord.bind(this);
      this.validateInputs = this.validateInputs.bind(this);
      this.createRecord = this.createRecord.bind(this);
      this.retrieveRecord = this.retrieveRecord.bind(this);
      this.updateRecord = this.updateRecord.bind(this);
      this.deleteRecord = this.deleteRecord.bind(this);
      this.resetAll = this.resetAll.bind(this);
   };

   setSearchedName(evt) {
     this.setState({searchedName: evt.target.value});

   };

   setId(evt) {
       this.setState({id: evt.target.value});

   };

  setFirstName(evt) {
      this.setState({firstName: evt.target.value});

  };

  setLastName(evt) {
      this.setState({lastName: evt.target.value});

  };

  setEmail(evt) {
      this.setState({email: evt.target.value});

  };

  setAddressLine1(evt) {
      this.setState({addressLine1: evt.target.value});

  };

  setAddressLine2(evt) {
      this.setState({addressLine2: evt.target.value});

  };

  setTown(evt) {
      this.setState({town: evt.target.value});

  };


  setPostalCode(evt) {
      this.setState({postalCode: evt.target.value});

  };

  setCountry(evt) {
      this.setState({country: evt.target.value});

  };

  setPhoneNumber1(evt) {
      this.setState({phoneNumber1: evt.target.value});

  };

  setPhoneNumber2(evt) {
      this.setState({phoneNumber2: evt.target.value});

  };

  setPhoneNumber3(evt) {
      this.setState({phoneNumber3: evt.target.value});

  };

  resetAll() {
      this.setState({
          searchedName: '',
          id: '',
          firstName: '',
          lastName: '',
          email: '',
          addressLine1: '',
          addressLine2: '',
          town: '',
          postalCode: '',
          country: '',
          phoneNumber1: '',
          phoneNumber2: '',
          phoneNumber3: '',
          actionSuccessful: false,
          actionMessage: ''
      });
  };

  validateInputs() {
      var id = this.state.id > 0;
      var firstName = this.state.firstName.length > 0;
      var lastName = this.state.lastName.length > 0;
      var email = this.state.email.length > 0;
      var addressLine1 = this.state.addressLine1.length > 0;
      var town = this.state.town.length > 0;
      var country = this.state.country.length > 0;

      return id && firstName && lastName && email && addressLine1 && town && country;
  };

  findRecord(searchedName) {
    $.ajax({
        url: "http://localhost:8080/CognizantUserData/Find?name="
            + searchedName,
        dataType: 'json',
        cache: false,
        success: function(data) {
            var array = [];
            for (let prop in data) {
                array.push(data[prop]);
            }

            if(array.length > 0){
              this.setState({
                actionSuccessful: true,
                actionMessage: 'Record was received from the server',
                id: array[0],
                firstName: array[1],
                lastName: array[2],
                email: array[3],
                addressLine1: array[4],
                addressLine2: array[5],
                town: array[6],
                postalCode: array[7],
                country: array[8],
                phoneNumber1: array[9],
                phoneNumber2: array[10],
                phoneNumber3: array[11]});
            } else {
                this.setState({actionSucessful: false,
                  actionMessage: 'Record was not received from the server'});
            }
        }.bind(this),
        error: function(xhr, status, err) {
            console.error(this.props.url, status, err.toString());
        }.bind(this)
    });
   };

   createRecord() {
     if(this.validateInputs()){
         $.ajax({
             url: "http://localhost:8080/CognizantUserData/Create?id="
             + this.state.id
             + "&firstName="
             + this.state.firstName
             + "&lastName="
             + this.state.lastName
             + "&email="
             + this.state.email
             + "&addressLine1="
             + this.state.addressLine1
             + "&addressLine2="
             + this.state.addressLine2
             + "&town="
             + this.state.town
             + "&postalCode="
             + this.state.postalCode
             + "&country="
             + this.state.country
             + "&phoneNumber1="
             + this.state.phoneNumber1
             + "&phoneNumber2="
             + this.state.phoneNumber2
             + "&phoneNumber3="
             + this.state.phoneNumber3
             + "&receivedDate="
             + this.state.receivedDate,
             dataType: 'json',
             cache: false,
             success: function(data) {
                 var array = [];
                 for (let prop in data) {
                     array.push(data[prop]);
                 }

                 if(data){
                     this.setState({actionSuccessful: true,
                       actionMessage: 'Record created successfully'});
                 }else {
                     this.setState({actionSuccessful: false,
                       actionMessage: 'Record creation failed'});
                 }

             }.bind(this),
             error: function(xhr, status, err) {
                 console.error(this.props.url, status, err.toString());
             }.bind(this)
         });
     } else {
         this.setState({actionSuccessful: false,
           actionMessage: 'Please enter mandatory data'});
     }
  };

  retrieveRecord() {
      $.ajax({
          url: "http://localhost:8080/CognizantUserData/Retrieve?firstName="
              + this.state.firstName
              + "&lastName="
              + this.state.lastName,
          dataType: 'json',
          cache: false,
          success: function(data) {
              var array = [];
              for (let prop in data) {
                  array.push(data[prop]);
              }

              if(array.length > 0) {
                this.setState({
                  actionSuccessful: true,
                  actionMessage: 'Record was found',
                  id: array[0],
                  firstName: array[1],
                  lastName: array[2],
                  email: array[3],
                  addressLine1: array[4],
                  addressLine2: array[5],
                  town: array[6],
                  postalCode: array[7],
                  country: array[8],
                  phoneNumber1: array[9],
                  phoneNumber2: array[10],
                  phoneNumber3: array[11]});

              } else {
                  this.setState({actionSuccessful: false,
                    actionMessage: 'Record was not found'});
              }
          }.bind(this),
          error: function(xhr, status, err) {
              console.error(this.props.url, status, err.toString());
          }.bind(this)
      });
  };

  updateRecord() {
      if(this.validateInputs()){
          $.ajax({
              url: "http://localhost:8080/CognizantUserData/Update?id="
              + this.state.id
              + "&firstName="
              + this.state.firstName
              + "&lastName="
              + this.state.lastName
              + "&email="
              + this.state.email
              + "&addressLine1="
              + this.state.addressLine1
              + "&addressLine2="
              + this.state.addressLine2
              + "&town="
              + this.state.town
              + "&postalCode="
              + this.state.postalCode
              + "&country="
              + this.state.country
              + "&phoneNumber1="
              + this.state.phoneNumber1
              + "&phoneNumber2="
              + this.state.phoneNumber2
              + "&phoneNumber3="
              + this.state.phoneNumber3,
              dataType: 'json',
              cache: false,
              success: function(data) {
                  if(data){
                      this.setState({actionSuccessful: true,
                        actionMessage: 'Record updated successfully'});
                  }else {
                      this.setState({actionSuccessful: false,
                        actionMessage: 'Record updating failed'});
                  }

              }.bind(this),
              error: function(xhr, status, err) {
                  console.error(this.props.url, status, err.toString());
              }.bind(this)
          });
      } else {
          this.setState({actionSuccessful: false,
            actionMessage: 'Please enter mandatory'});
      }
  };

  deleteRecord() {
      $.ajax({
          url: "http://localhost:8080/CognizantUserData/Delete?id="
              + this.state.id,
          dataType: 'json',
          cache: false,
          success: function(data) {
              if(data){
                  this.resetAll();
                  this.setState({actionSuccessful: true,
                    actionMessage: 'Record deleted successfully'});

              }else {
                  this.setState({actionSuccessful: false,
                    actionMessage: 'Record deletion failed'});
              }

          }.bind(this),
          error: function(xhr, status, err) {
              console.error(this.props.url, status, err.toString());
          }.bind(this)
      });
  };

   render() {
      return (
         <div className="userControls">
            <FindSection
            	searchedName={this.state.searchedName}
            	setSearchedName={this.setSearchedName}
            	findRecord={this.findRecord}
            	createRecord={this.createRecord}/>

            <InputOutputSection
              id={this.state.id}
              setId={this.setId}
              firstName={this.state.firstName}
            	setFirstName={this.setFirstName}
            	lastName={this.state.lastName}
            	setLastName={this.setLastName}
            	email={this.state.email}
            	setEmail={this.setEmail}
            	addressLine1={this.state.addressLine1}
            	setAddressLine1={this.setAddressLine1}
            	addressLine2={this.state.addressLine2}
            	setAddressLine2={this.setAddressLine2}
            	town={this.state.town}
            	setTown={this.setTown}
            	postalCode={this.state.postalCode}
            	setPostalCode={this.setPostalCode}
            	country={this.state.country}
            	setCountry={this.setCountry}
            	phoneNumber1={this.state.phoneNumber1}
            	setPhoneNumber1={this.setPhoneNumber1}
            	phoneNumber2={this.state.phoneNumber2}
            	setPhoneNumber2={this.setPhoneNumber2}
            	phoneNumber3={this.state.phoneNumber3}
            	setPhoneNumber3={this.setPhoneNumber3}/>

              <CommandSection
                createRecord={this.createRecord}
                retrieveRecord={this.retrieveRecord}
                updateRecord={this.updateRecord}
                deleteRecord={this.deleteRecord}
                resetAll={this.resetAll}/>

                <br/>

              <MessageSection
                actionSuccessful={this.state.actionSuccessful}
                actionMessage={this.state.actionMessage}/>

         </div>
      );
   }
}

class FindSection extends React.Component {
   render() {
      return (
         <div className="findSection">
            Find in server  <input type="text" value={this.props.searchedName} onChange={this.props.setSearchedName}/><br/><br/>
            <button className="findButton" onClick={()=>this.props.findRecord(this.props.searchedName)}> Find </button>
            <button className="saveButton" onClick={this.props.createRecord}> Save Locally </button>

         </div>
      );
   }
}

class InputOutputSection extends React.Component {
   render() {
      return (
         <div>
            <div className="primaryDetails">
              <label>ID*  </label><input type="text" value={this.props.id} onChange={this.props.setId}/><br/><br/>

              <label>First Name*</label> <input type="text" value={this.props.firstName} onChange={this.props.setFirstName}/>
              <label>Last Name*</label> <input type="text" value={this.props.lastName} onChange={this.props.setLastName}/>
              <br/><br/>

              <label>Email*</label> <input type="text" value={this.props.email} onChange={this.props.setEmail}/>
              <br/><br/><br/>
            </div>

            <div className="addressDetails">
              <label>Address Line1*</label> <input type="text" value={this.props.addressLine1} onChange={this.props.setAddressLine1}/>
              <label>Address Line2</label> <input type="text" value={this.props.addressLine2} onChange={this.props.setAddressLine2}/>
              <br/><br/>

              <label>Town*</label> <input type="text" value={this.props.town} onChange={this.props.setTown}/>
              <label>Postal Code</label> <input type="text" value={this.props.postalCode} onChange={this.props.setPostalCode}/>
              <br/><br/>

              <label>Country*</label> <input type="text" value={this.props.country} onChange={this.props.setCountry}/>
              <br/><br/><br/>
            </div>

            <div className="phoneNumberDetails">
              <label>Phone Number1</label> <input type="text" value={this.props.phoneNumber1} onChange={this.props.setPhoneNumber1}/><br/><br/>
              <label>Phone Number2</label> <input type="text" value={this.props.phoneNumber2} onChange={this.props.setPhoneNumber2}/><br/><br/>
              <label>Phone Number3</label> <input type="text" value={this.props.phoneNumber3} onChange={this.props.setPhoneNumber3}/><br/><br/>
            </div>
         </div>
      );
   }
}

class CommandSection extends React.Component {
   render() {
      return (
         <div className="buttonPanel">
            <button className="actionButton" onClick={this.props.createRecord}> Create </button>
            <button className="actionButton" onClick={this.props.retrieveRecord}> Retrieve </button>
            <button className="actionButton" onClick={this.props.updateRecord}> Update </button>
            <button className="actionButton" onClick={this.props.deleteRecord}> Delete </button><br/>
            <button className="actionButton" onClick={this.props.resetAll}> Clear </button>
         </div>
      );
   }
}

class MessageSection extends React.Component {
   render() {
      return (
        <div className="messageArea">
          <div className={this.props.actionSuccessful ? "actionSuccessful" : "actionFailed"}>
            <br/><span>{this.props.actionMessage}</span>
          </div>
        </div>

      );
   }
}

ReactDOM.render(<CongnizantUserData />, document.getElementById('container'));
