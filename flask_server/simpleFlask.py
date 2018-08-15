import flask
from flask_cors import CORS
app=flask.Flask(__name__)
#cors = CORS(app, resources={r"/simpleApi/*": {"origins": "*"},r"/authenApi/*": {"origins": "http://hackmyteeth.com", "supports_credentials": True}})
dict_user= {"1":{"name":"Ardyanto Songoku","phone":"0912221121","salt":"khct9ok4"},"4":{"name":"Tin Tran","phone":"093332221","salt":"9bqtepv0"}}
@app.route("/",methods=["GET"])
def index():
    res = flask.make_response()
    res.set_cookie("pageCookie", value="1")
@app.route("/simpleApi/<userid>",methods=["GET"])
def getSimpleApi(userid):
    return flask.jsonify(
        name=dict_user[userid]["name"],
        phone=dict_user[userid]["phone"]
    )
@app.route("/authenApi/<userid>",methods=["GET"])
def getAuthenApi(userid):
    return flask.jsonify(
        name=dict_user[userid]["name"],
        phone=dict_user[userid]["phone"],
        salt=dict_user[userid]["salt"]
    )
if __name__ == "__main__":
    app.run( host='0.0.0.0', port=5000, debug = True)


