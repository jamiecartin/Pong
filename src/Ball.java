public class Ball {
    public Rect rect;
    public Rect leftPaddle, rightPaddle;
    public Text leftScoreText, rightScoreText;

    private double vy = 10.0;
    private double vx = -200.0;
    //velocity x, y

    public Ball(Rect rect, Rect leftPaddle, Rect rightPaddle, Text leftScoreText, Text rightScoreText) {
        this.rect = rect;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.leftScoreText = leftScoreText;
        this.rightScoreText = rightScoreText;
    }

    public double calculateNewVelocityAngle(Rect paddle) {
        double relativeIntersectY = (paddle.y + (paddle.height / 2.0)) - (this.rect.y + (this.rect.height / 2.0));
        double normalIntersectY = relativeIntersectY / (paddle.height / 2.0);
        double theta = normalIntersectY * Constants.MAX_ANGLE;
        return Math.toRadians(theta);
    }

    public void update(double dt) {
        //left
        if (vx < 0.0) {
            if (this.rect.x + (vx * dt) < leftPaddle.x + leftPaddle.width) {
                if (this.rect.y + (vy * dt) > leftPaddle.y &&
                        this.rect.y + (vy * dt) + this.rect.height < leftPaddle.y + leftPaddle.height) {
                    double theta = calculateNewVelocityAngle(leftPaddle);
                    double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                    double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED;

                    double oldSign = Math.signum(vx);
                    this.vx = newVx * (-1.0 * oldSign);
                    this.vy = newVy;
                }
            }
        } else if (vx >= 0.0) {
            if (this.rect.x + (vx * dt) + rect.width > rightPaddle.x) {
                if (this.rect.y + (vy * dt) > rightPaddle.y &&
                        this.rect.y + (vy * dt) + this.rect.height < rightPaddle.y + rightPaddle.height) {
                    double theta = calculateNewVelocityAngle(rightPaddle);
                    double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                    double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED;

                    double oldSign = Math.signum(vx);
                    this.vx = newVx * (-1.0 * oldSign);
                    this.vy = newVy;
                }

            }
        }

        if (vy >= 0.0) {
            if (this.rect.y + (vy * dt) + this.rect.height > Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM) {
                this.vy *= -1.0;
            }
        } else if (vy < 0) {
            if (this.rect.y + (vy * dt) < Constants.TOOLBAR_HEIGHT) {
                this.vy *= -1.0;
            }
        }
        //position = position + velocity
        //velocity = velocity + acceleration
        this.rect.x += vx * dt;
        this.rect.y += vy * dt;

        if (this.rect.x + this.rect.width < leftPaddle.x) {
            int rightScore = Integer.parseInt(rightScoreText.text);
            rightScore++;
            rightScoreText.text = "" + rightScore;
            this.rect.x = Constants.SCREEN_WIDTH / 2.0;
            this.rect.y = Constants.SCREEN_HEIGHT / 2.0;
            this.vx = -150.0;
            this.vy = 10.0;
            if (rightScore >= Constants.WIN_SCORE) {
                Main.changeState(0);
            }
        } else if (this.rect.x > rightPaddle.x + rightPaddle.width) {
            int leftScore = Integer.parseInt(leftScoreText.text);
            leftScore++;
            leftScoreText.text = "" + leftScore;
            this.rect.x = Constants.SCREEN_WIDTH / 2.0;
            this.rect.y = Constants.SCREEN_HEIGHT / 2.0;
            this.vx = 150.0;
            this.vy = 10.0;
            if (leftScore >= Constants.WIN_SCORE) {
                Main.changeState(2);
            }
        }
    }
}
